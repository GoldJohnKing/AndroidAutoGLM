package com.sidhu.androidautoglm.utils

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.vosk.Recognizer
import org.vosk.android.RecognitionListener
import org.vosk.android.SpeechService

class SpeechRecognizerManager(private val context: Context) {

    private var speechService: SpeechService? = null
    private val _isListening = MutableStateFlow(false)
    val isListening: StateFlow<Boolean> = _isListening

    private val _soundLevel = MutableStateFlow(0f)
    val soundLevel: StateFlow<Float> = _soundLevel

    private var onResult: ((String) -> Unit)? = null
    private var onError: ((String) -> Unit)? = null

    // For simulated sound level
    private var soundLevelJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    fun startListening(
        onResultCallback: (String) -> Unit,
        onErrorCallback: (String) -> Unit
    ) {
        val model = VoskModelManager.model
        if (model == null) {
            onErrorCallback("Model not loaded yet")
            return
        }

        if (_isListening.value) return

        onResult = onResultCallback
        onError = onErrorCallback

        try {
            val recognizer = Recognizer(model, 16000.0f)
            speechService = SpeechService(recognizer, 16000.0f)
            speechService?.startListening(object : RecognitionListener {
                override fun onPartialResult(hypothesis: String?) {
                    // hypothesis is JSON: { "partial" : "text" }
                }

                override fun onResult(hypothesis: String?) {
                    // hypothesis is JSON: { "text" : "text" }
                    if (hypothesis != null) {
                        try {
                            val json = JSONObject(hypothesis)
                            val text = json.optString("text", "")
                            if (text.isNotEmpty()) {
                                onResult?.invoke(text)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFinalResult(hypothesis: String?) {
                    // hypothesis is JSON: { "text" : "text" }
                    if (hypothesis != null) {
                        try {
                            val json = JSONObject(hypothesis)
                            val text = json.optString("text", "")
                            if (text.isNotEmpty()) {
                                onResult?.invoke(text)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onError(exception: Exception?) {
                    _isListening.value = false
                    stopSimulatingSoundLevel()
                    onError?.invoke(exception?.message ?: "Unknown error")
                }

                override fun onTimeout() {
                    _isListening.value = false
                    stopSimulatingSoundLevel()
                    onError?.invoke("Timeout")
                }
            })
            _isListening.value = true
            startSimulatingSoundLevel()
        } catch (e: Exception) {
            e.printStackTrace()
            _isListening.value = false
            stopSimulatingSoundLevel()
            onErrorCallback(e.message ?: "Failed to start Vosk")
        }
    }

    suspend fun stopListening() {
        withContext(Dispatchers.IO) {
            try {
                speechService?.stop()
                speechService?.shutdown()
                speechService = null
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isListening.value = false
                _soundLevel.value = 0f
                stopSimulatingSoundLevel()
            }
        }
    }
    
    suspend fun cancel() {
        stopListening()
    }

    suspend fun reset() {
        stopListening()
    }

    fun destroy() {
        try {
            speechService?.shutdown()
            speechService = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        stopSimulatingSoundLevel()
    }
    
    private fun startSimulatingSoundLevel() {
        soundLevelJob?.cancel()
        soundLevelJob = scope.launch {
            while (_isListening.value) {
                // Random float between -2 and 10
                val randomLevel = -2f + (Math.random() * 12f).toFloat()
                _soundLevel.value = randomLevel
                delay(100)
            }
            _soundLevel.value = 0f
        }
    }
    
    private fun stopSimulatingSoundLevel() {
        soundLevelJob?.cancel()
        soundLevelJob = null
        _soundLevel.value = 0f
    }
}
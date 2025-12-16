package com.sidhu.androidautoglm.utils

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import org.vosk.Model
import java.io.InputStream
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.zip.ZipInputStream

object VoskModelManager {
    private val _modelState = MutableStateFlow<ModelState>(ModelState.NotInitialized)
    val modelState: StateFlow<ModelState> = _modelState

    var model: Model? = null
        private set

    sealed class ModelState {
        object NotInitialized : ModelState()
        data class Downloading(val progress: Float) : ModelState() // 0.0 to 1.0
        object Unzipping : ModelState()
        object Loading : ModelState()
        object Ready : ModelState()
        data class Error(val message: String) : ModelState()
    }

    private const val MODEL_CN_DIR_NAME = "vosk-model-small-cn-0.22"
    private const val MODEL_EN_DIR_NAME = "vosk-model-small-en-us-0.15"

    private val _loadedLanguage = MutableStateFlow<String?>(null)
    val loadedLanguage: StateFlow<String?> = _loadedLanguage

    fun isModelDownloaded(context: Context, languageCode: String = "zh"): Boolean {
        val modelDirName = if (languageCode == "en") MODEL_EN_DIR_NAME else MODEL_CN_DIR_NAME
        val destinationDir = File(context.getExternalFilesDir(null), "models")
        val modelFile = File(destinationDir, modelDirName)
        return modelFile.exists()
    }

    suspend fun initModel(context: Context, languageCode: String = "zh") {
        if (model != null && _loadedLanguage.value == languageCode) {
            _modelState.value = ModelState.Ready
            return
        }

        // Reset state if switching language
        if (_loadedLanguage.value != languageCode) {
            model?.close()
            model = null
            _modelState.value = ModelState.NotInitialized
        }

        _loadedLanguage.value = languageCode
        val modelDirName = if (languageCode == "en") MODEL_EN_DIR_NAME else MODEL_CN_DIR_NAME
        
        // Indicate loading started
        _modelState.value = ModelState.Loading

        withContext(Dispatchers.IO) {
            try {
                val destinationDir = File(context.getExternalFilesDir(null), "models")
                if (!destinationDir.exists()) destinationDir.mkdirs()

                val modelFile = File(destinationDir, modelDirName)
                
                if (!modelFile.exists()) {
                    // Try install from Assets
                    val assetFileName = if (languageCode == "en") "model-en.zip" else "model-cn.zip"
                    try {
                        // Check if asset exists
                        val assets = context.assets.list("")
                        if (assets?.contains(assetFileName) == true) {
                            _modelState.value = ModelState.Unzipping
                            context.assets.open(assetFileName).use { inputStream ->
                                unzip(inputStream, destinationDir)
                            }
                        } else {
                            _modelState.value = ModelState.Error("Model file not found in assets: $assetFileName")
                            return@withContext
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        _modelState.value = ModelState.Error("Unzip failed: ${e.message}")
                        return@withContext
                    }
                }

                // Load model
                if (modelFile.exists()) {
                    model = Model(modelFile.absolutePath)
                    _modelState.value = ModelState.Ready
                } else {
                     _modelState.value = ModelState.Error("Model not found after unzip")
                }
                
            } catch (e: Exception) {
                e.printStackTrace()
                _modelState.value = ModelState.Error("Init failed: ${e.message}")
            }
        }
    }

    private fun unzip(inputStream: InputStream, targetDirectory: File) {
        ZipInputStream(BufferedInputStream(inputStream)).use { zipInputStream ->
            var entry = zipInputStream.nextEntry
            while (entry != null) {
                val filePath = File(targetDirectory, entry.name)
                
                if (entry.isDirectory) {
                    filePath.mkdirs()
                } else {
                    // Create parent directories if needed
                    filePath.parentFile?.mkdirs()
                    
                    FileOutputStream(filePath).use { outputStream ->
                        zipInputStream.copyTo(outputStream)
                    }
                }
                entry = zipInputStream.nextEntry
            }
        }
    }
}
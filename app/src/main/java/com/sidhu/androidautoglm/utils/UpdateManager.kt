package com.sidhu.androidautoglm.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import com.sidhu.androidautoglm.BuildConfig
import com.sidhu.androidautoglm.network.UpdateInfo
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object UpdateManager {

    private const val UPDATE_URL = "https://dl.sidhu.net.cn/releases/version.json"
    private const val TAG = "UpdateManager"

    fun checkUpdate(
        context: Context,
        onUpdateAvailable: (UpdateInfo) -> Unit,
        onNoUpdate: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        val url = "$UPDATE_URL?t=${System.currentTimeMillis()}"
        Log.d(TAG, "Checking update from: $url")
        val request = Request.Builder()
            .url(url)
            .header("Cache-Control", "no-cache")
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                Log.d(TAG, "Response: $json")
                if (json == null) {
                    mainThread { onError("Empty response") }
                    return
                }

                try {
                    val info = Gson().fromJson(json, UpdateInfo::class.java)
                    val currentVersionCode = BuildConfig.VERSION_CODE
                    Log.d(TAG, "Remote version: ${info.versionCode}, Local version: $currentVersionCode")

                    if (info.versionCode > currentVersionCode) {
                        Log.d(TAG, "Update available")
                        mainThread { onUpdateAvailable(info) }
                    } else {
                        Log.d(TAG, "No update")
                        mainThread { onNoUpdate() }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e(TAG, "Parse error", e)
                    mainThread { onError("Parse error: ${e.message}") }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Log.e(TAG, "Network error", e)
                mainThread { onError("Network error: ${e.message}") }
            }
        })
    }

    private fun mainThread(block: () -> Unit) {
        Handler(Looper.getMainLooper()).post(block)
    }
}

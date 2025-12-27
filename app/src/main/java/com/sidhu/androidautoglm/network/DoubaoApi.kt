package com.sidhu.androidautoglm.network

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

// === Retrofit Interface ===
interface DoubaoApi {
    @POST("chat/completions")
    suspend fun chatCompletion(
        @Header("Authorization") authorization: String,
        @Body request: DoubaoRequest
    ): Response<DoubaoResponse>
}

// === Data Models ===
data class DoubaoRequest(
    val model: String,
    val messages: List<DoubaoMessage>,
    @SerializedName("max_completion_tokens") val maxCompletionTokens: Int = 4096,
    val stream: Boolean = false,
    val temperature: Double? = null,
    @SerializedName("top_p") val topP: Double? = null,
    @SerializedName("frequency_penalty") val frequencyPenalty: Double? = null,
    @SerializedName("reasoning_effort") val reasoningEffort: String? = null
)

data class DoubaoMessage(
    val role: String,
    val content: Any // Can be String or List<DoubaoContentItem>
)

data class DoubaoContentItem(
    val type: String,
    val text: String? = null,
    @SerializedName("image_url") val imageUrl: DoubaoImageUrl? = null
)

data class DoubaoImageUrl(
    val url: String
)

data class DoubaoResponse(
    val choices: List<DoubaoChoice>?
)

data class DoubaoChoice(
    val message: DoubaoMessageResponse
)

data class DoubaoMessageResponse(
    val content: String
)

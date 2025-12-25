package com.sidhu.androidautoglm.network

data class UpdateInfo(
    val versionName: String,
    val versionCode: Int,
    val updateLog: String,
    val downloadUrl: String,
    val releasePage: String?,
    val releaseDate: String?
)

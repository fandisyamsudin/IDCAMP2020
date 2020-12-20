package com.jetpack.module012.data

import com.google.gson.annotations.SerializedName

data class DataResponse<T>(
        @SerializedName("status_code")
        val statusCode: Int? = null,
        @SerializedName("status_message")
        val statusMessage: String? = null,
        @SerializedName("results")
        val results: List<T>? = null
)
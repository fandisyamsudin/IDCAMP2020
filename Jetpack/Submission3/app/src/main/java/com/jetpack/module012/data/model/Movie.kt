package com.jetpack.module012.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("vote_average")
    val score: Double? = 0.0,
    @SerializedName("release_date")
    val release: String? = "",
    @SerializedName("original_language")
    val language: String? = "",
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("poster_path")
    val poster: String? = "",
    @SerializedName("backdrop_path")
    val backdrop: String? = ""
) : Parcelable
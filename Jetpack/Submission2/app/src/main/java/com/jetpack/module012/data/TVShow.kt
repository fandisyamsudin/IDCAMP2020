package com.jetpack.module012.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShow(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var title: String?,
        @SerializedName("vote_average")
        var score: Double?,
        @SerializedName("first_air_date")
        var release: String?,
        @SerializedName("original_language")
        var language: String?,
        @SerializedName("overview")
        var overview: String?,
        @SerializedName("poster_path")
        var poster: String?,
        @SerializedName("backdrop_path")
        var backdrop: String?,
) : Parcelable
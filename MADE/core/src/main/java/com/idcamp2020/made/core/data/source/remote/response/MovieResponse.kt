package com.idcamp2020.made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = 0.0,

    @field:SerializedName("title")
    val title: String? = "",

    @field:SerializedName("release_date")
    val releaseDate: String? = "",

    @field:SerializedName("overview")
    val overview: String? = "",

    @field:SerializedName("poster_path")
    val posterPath: String? = "",

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = "",
)

package com.idcamp2020.made.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
        val id: Int = 0,
        val voteAverage: Double? = 0.0,
        val title: String? = "",
        val releaseDate: String? = "",
        val overview: String? = "",
        val poster: String? = "",
        val backdrop: String? = "",
        val isFavorite: Boolean = false
): Parcelable

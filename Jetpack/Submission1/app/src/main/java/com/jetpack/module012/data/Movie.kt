package com.jetpack.module012.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    var id: Int,
    var poster: Int,
    var title: String?,
    var score: String?,
    var release: String?,
    var duration: String?,
    var description: String?,
    var genre: String?
) : Parcelable
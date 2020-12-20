package com.jetpack.module012.data.room

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favorite_movie")
@Parcelize
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String? = "",
    @ColumnInfo(name = "vote_average")
    val score: Double? = 0.0,
    @ColumnInfo(name = "release")
    val release: String? = "",
    @ColumnInfo(name = "original_language")
    val language: String? = "",
    @ColumnInfo(name = "overview")
    val overview: String? = "",
    @ColumnInfo(name = "poster_path")
    val poster: String? = "",
    @ColumnInfo(name = "backdrop_path")
    val backdrop: String? = "",
    @NonNull
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
) : Parcelable
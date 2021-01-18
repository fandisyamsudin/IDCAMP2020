package com.idcamp2020.made.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_favorite")
data class MovieEntity(
        @PrimaryKey (autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "id")
        val id: Int = 0,

        @ColumnInfo(name = "vote_average")
        val voteAverage: Double? = 0.0,

        @ColumnInfo(name = "title")
        val title: String? = "",

        @ColumnInfo(name = "release_date")
        val releaseDate: String? = "",

        @ColumnInfo(name = "overview")
        val overview: String? = "",

        @ColumnInfo(name = "poster_path")
        val posterPath: String? = "",

        @ColumnInfo(name = "backdrop_path")
        val backdropPath: String? = "",

        @ColumnInfo(name = "is_favorite")
        var isFavorite: Boolean = false
)

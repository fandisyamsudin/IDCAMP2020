package com.idcamp2020.made.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val POPULARITY = "Popularity"
    const val VOTE = "Vote"
    const val NEWEST = "Newest"
    const val RANDOM = "Random"

    fun getSortedMovie(query: String): SimpleSQLiteQuery {
        val sorted = StringBuilder().append("SELECT * FROM table_favorite")
        when (query) {
            POPULARITY -> {
                sorted.append("ORDER BY popularity DESC")
            }
            NEWEST -> {
                sorted.append("ORDER BY releaseDate DESC")
            }
            VOTE -> {
                sorted.append("ORDER BY voteAverage DESC")
            }
            RANDOM -> {
                sorted.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(sorted.toString())
    }

    fun getSortedMovieFavorite(query: String): SimpleSQLiteQuery {
        val sorted = StringBuilder().append("SELECT * FROM table_favorite where is_favorite = 1")
        when (query) {
            POPULARITY -> {
                sorted.append("ORDER BY popularity DESC")
            }
            NEWEST -> {
                sorted.append("ORDER BY releaseDate DESC")
            }
            VOTE -> {
                sorted.append("ORDER BY voteAverage DESC")
            }
            RANDOM -> {
                sorted.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(sorted.toString())
    }

}
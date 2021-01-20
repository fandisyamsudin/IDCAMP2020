package com.idcamp2020.made.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "Newest"

    fun getSortedMovie(query: String): SimpleSQLiteQuery {
        val sorted = StringBuilder().append("SELECT * FROM table_favorite ")
        when (query) {
            NEWEST -> {
                sorted.append("ORDER BY release_date DESC")
            }
        }
        return SimpleSQLiteQuery(sorted.toString())
    }

    fun getSortedMovieFavorite(query: String): SimpleSQLiteQuery {
        val sorted = StringBuilder().append("SELECT * FROM table_favorite where is_favorite = 1 ")
        when (query) {
            NEWEST -> {
                sorted.append("ORDER BY release_date DESC")
            }
        }
        return SimpleSQLiteQuery(sorted.toString())
    }
}
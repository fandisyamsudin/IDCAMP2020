package com.idcamp2020.made.core.data.source.local

import com.idcamp2020.made.core.data.source.local.entity.MovieEntity
import com.idcamp2020.made.core.data.source.local.room.MovieDao
import com.idcamp2020.made.core.utils.SortUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(private val movieDao: MovieDao) {
    fun getAllMovie(query: String): Flow<List<MovieEntity>> {
        val queryDao = SortUtils.getSortedMovie(query)
        return movieDao.getMovieEntity(queryDao)
    }

    fun getAllMovieFavorite(query: String): Flow<List<MovieEntity>> {
        val queryDao = SortUtils.getSortedMovieFavorite(query)
        return movieDao.getMovieFavorite(queryDao)
    }

    suspend fun insertMovieFavorite(movies: List<MovieEntity>) {
        return movieDao.insertMovieFavorite(movies)
    }

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateMovieFavorite(movie)
    }

    fun getMovieSearch(query: String): Flow<List<MovieEntity>> {
        return movieDao.getMovieSearch(query)
            .flowOn(Dispatchers.Default)
            .conflate()
    }
}
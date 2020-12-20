package com.jetpack.module012.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.jetpack.module012.data.room.Dao
import com.jetpack.module012.data.room.MovieEntity
import com.jetpack.module012.data.room.TVShowEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: Dao) {
    fun getMovies(): DataSource.Factory<Int, MovieEntity> = dao.getMovies()

    fun getTVShows(): DataSource.Factory<Int, TVShowEntity> = dao.getTVShows()

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = dao.getFavoriteMovies()

    fun getFavoriteTV(): DataSource.Factory<Int, TVShowEntity> = dao.getFavoriteTV()

    fun getMovieById(id: Int): LiveData<MovieEntity> = dao.getMovieById(id)

    fun getTVById(id: Int): LiveData<TVShowEntity> = dao.getTVById(id)

    fun insertFavoriteMovie(movies: List<MovieEntity>) = dao.insertFavoriteMovie(movies)

    fun insertFavoriteTV(tvShows: List<TVShowEntity>) = dao.insertFavoriteTV(tvShows)

    fun setFavoriteMovie(movie: MovieEntity) {
        movie.favorite = !movie.favorite
        dao.updateFavoriteMovie(movie)
    }

    fun setFavoriteTV(tvShow: TVShowEntity) {
        tvShow.favorite = !tvShow.favorite
        dao.updateFavoriteTV(tvShow)
    }
}
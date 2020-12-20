package com.jetpack.module012.data.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertFavoriteMovie(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TVShowEntity::class)
    fun insertFavoriteTV(tvShows: List<TVShowEntity>)

    @Update(entity = MovieEntity::class)
    fun updateFavoriteMovie(movie: MovieEntity)

    @Update(entity = TVShowEntity::class)
    fun updateFavoriteTV(tvShows: TVShowEntity)

    @Query("SELECT * FROM favorite_movie")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM favorite_tv")
    fun getTVShows(): DataSource.Factory<Int, TVShowEntity>

    @Query("SELECT * FROM favorite_movie WHERE favorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM favorite_tv WHERE favorite = 1")
    fun getFavoriteTV(): DataSource.Factory<Int, TVShowEntity>

    @Query("SELECT * FROM favorite_movie WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM favorite_tv WHERE id = :id")
    fun getTVById(id: Int): LiveData<TVShowEntity>
}
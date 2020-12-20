package com.jetpack.module012.data

import androidx.lifecycle.LiveData

interface CatalogDataSource {

    fun getMovies(): LiveData<List<Movie>>

    fun getMovieDetail(movieId: Int): LiveData<Movie>

    fun getTVShow(): LiveData<List<TVShow>>

    fun getTvShowDetail(tvShowId: Int): LiveData<TVShow>

}
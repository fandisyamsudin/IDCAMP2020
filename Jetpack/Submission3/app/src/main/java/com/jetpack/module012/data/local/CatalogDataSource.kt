package com.jetpack.module012.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jetpack.module012.data.room.MovieEntity
import com.jetpack.module012.data.room.TVShowEntity
import com.jetpack.module012.vo.Resource

interface CatalogDataSource {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getMovieById(id: Int): LiveData<MovieEntity>

    fun getTVShow(): LiveData<Resource<PagedList<TVShowEntity>>>

    fun getFavoriteTV(): LiveData<PagedList<TVShowEntity>>

    fun getTVById(id: Int): LiveData<TVShowEntity>

    fun setFavoriteMovie(movie: MovieEntity)

    fun setFavoriteTV(tvShow: TVShowEntity)

}
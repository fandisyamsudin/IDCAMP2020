package com.jetpack.module012.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.module012.data.repository.CatalogRepository
import com.jetpack.module012.data.room.MovieEntity
import javax.inject.Inject

class MovieFavoriteViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> =
        catalogRepository.getFavoriteMovies()
}
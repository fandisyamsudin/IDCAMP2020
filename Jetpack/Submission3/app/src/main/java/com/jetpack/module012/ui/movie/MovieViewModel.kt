package com.jetpack.module012.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.module012.data.repository.CatalogRepository
import com.jetpack.module012.data.room.MovieEntity
import com.jetpack.module012.vo.Resource
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = catalogRepository.getMovies()
}
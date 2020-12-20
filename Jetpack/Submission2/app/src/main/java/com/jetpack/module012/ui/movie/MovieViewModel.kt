package com.jetpack.module012.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jetpack.module012.data.CatalogRepository
import com.jetpack.module012.data.Movie
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
        ViewModel() {

    fun getMovies(): LiveData<List<Movie>> = catalogRepository.getMovies()
}
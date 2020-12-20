package com.jetpack.module012.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jetpack.module012.data.CatalogRepository
import com.jetpack.module012.data.Movie
import com.jetpack.module012.data.TVShow
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
        ViewModel() {

    fun getDetailMovies(movieId: Int): LiveData<Movie> = catalogRepository.getMovieDetail(movieId)

    fun getDetailTVSHows(tvShowId: Int): LiveData<TVShow> =
            catalogRepository.getTvShowDetail(tvShowId)
}

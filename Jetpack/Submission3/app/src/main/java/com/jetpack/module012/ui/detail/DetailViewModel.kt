package com.jetpack.module012.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jetpack.module012.data.repository.CatalogRepository
import com.jetpack.module012.data.room.MovieEntity
import com.jetpack.module012.data.room.TVShowEntity
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {

    fun getDetailMovies(id: Int): LiveData<MovieEntity> = catalogRepository.getMovieById(id)

    fun getDetailTVSHows(id: Int): LiveData<TVShowEntity> = catalogRepository.getTVById(id)

    fun setFavoriteMovie(movie: MovieEntity) {
        catalogRepository.setFavoriteMovie(movie)
    }

    fun setFavoriteTV(tvShow: TVShowEntity) {
        catalogRepository.setFavoriteTV(tvShow)
    }
}

package com.idcamp2020.made.ui.detail

import androidx.lifecycle.ViewModel
import com.idcamp2020.made.core.domain.model.Movie
import com.idcamp2020.made.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setMovieFavorite(movie: Movie, newStatus: Boolean) {
        movieUseCase.setMovieFavorite(movie, newStatus)
    }
}
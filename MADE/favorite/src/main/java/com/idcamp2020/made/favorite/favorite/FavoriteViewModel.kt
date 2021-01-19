package com.idcamp2020.made.favorite.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idcamp2020.made.core.domain.model.Movie
import com.idcamp2020.made.core.domain.usecase.MovieUseCase

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getMovieFavorite(query: String): LiveData<List<Movie>> =
        movieUseCase.getMovieFavorite(query).asLiveData()
}
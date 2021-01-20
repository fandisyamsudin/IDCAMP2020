package com.idcamp2020.made.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idcamp2020.made.core.data.Resource
import com.idcamp2020.made.core.domain.model.Movie
import com.idcamp2020.made.core.domain.usecase.MovieUseCase

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getMovie(query: String): LiveData<Resource<List<Movie>>>{
        return movieUseCase.getMovie(query).asLiveData()
    }
}
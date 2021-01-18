package com.idcamp2020.made.favorite.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idcamp2020.made.core.domain.usecase.MovieUseCase

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}
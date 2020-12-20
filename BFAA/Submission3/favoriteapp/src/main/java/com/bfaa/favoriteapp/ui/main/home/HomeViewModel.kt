package com.bfaa.favoriteapp.ui.main.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bfaa.favoriteapp.repositories.HomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    fun getUsers(context: Context) = repository.getUsersFavorite(context)
    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}

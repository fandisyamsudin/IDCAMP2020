package com.bfaa.idchamp2020.ui.main.favorite

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bfaa.idchamp2020.repositories.FavoriteRepository
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
): ViewModel() {
    fun getUsers(context: Context) = repository.getUsersFavorite(context)
    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}
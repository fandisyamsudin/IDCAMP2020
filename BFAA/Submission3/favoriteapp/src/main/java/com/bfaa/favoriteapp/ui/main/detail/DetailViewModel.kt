package com.bfaa.favoriteapp.ui.main.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bfaa.favoriteapp.repositories.ProfileRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {
    fun getDetail(username: String) = repository.getUserDetail(username)

    fun checkFavoriteUser(userId: Int, context: Context) =
        repository.checkFavoriteUser(userId, context)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}

package com.bfaa.idchamp2020.ui.main.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bfaa.idchamp2020.model.UserGithub
import com.bfaa.idchamp2020.repositories.ProfileRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {
    fun getDetail(username: String) = repository.getUserDetail(username)
    fun checkFavoriteUser(userId: Int, context: Context) =
        repository.checkFavoriteUser(userId, context)
    fun addFavoriteUser(user: UserGithub, context: Context) =
        repository.addFavoriteUser(user, context)
    fun deleteFavoriteUser(user: UserGithub, context: Context) =
        repository.deleteFavoriteUser(user, context)
    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}

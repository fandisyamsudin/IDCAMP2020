package com.bfaa.idchamp2020.ui.main.detail

import androidx.lifecycle.ViewModel
import com.bfaa.idchamp2020.repositories.UserRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    fun getDetail(username: String) = repository.getUserDetail(username)
    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}

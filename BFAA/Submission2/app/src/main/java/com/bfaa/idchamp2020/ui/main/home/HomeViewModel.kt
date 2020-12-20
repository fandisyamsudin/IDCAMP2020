package com.bfaa.idchamp2020.ui.main.home

import androidx.lifecycle.ViewModel
import com.bfaa.idchamp2020.repositories.UserRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    fun getUserSearch(keyword: String) = repository.getUserSearch(keyword)
    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}

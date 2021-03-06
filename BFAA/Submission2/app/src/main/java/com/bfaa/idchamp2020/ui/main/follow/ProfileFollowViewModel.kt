package com.bfaa.idchamp2020.ui.main.follow

import androidx.lifecycle.ViewModel
import com.bfaa.idchamp2020.repositories.UserRepository
import javax.inject.Inject

class ProfileFollowViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    fun getFollowers(username: String) = repository.getUserFollower(username)
    fun getFollowing(username: String) = repository.getUserFollowing(username)
    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}

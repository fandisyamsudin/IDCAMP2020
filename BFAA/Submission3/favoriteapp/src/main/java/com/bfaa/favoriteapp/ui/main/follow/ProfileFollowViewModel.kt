package com.bfaa.favoriteapp.ui.main.follow

import androidx.lifecycle.ViewModel
import com.bfaa.favoriteapp.repositories.ProfileRepository
import javax.inject.Inject

class ProfileFollowViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {
    fun getFollowers(username: String) = repository.getUserFollower(username)
    fun getFollowing(username: String) = repository.getUserFollowing(username)
    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}

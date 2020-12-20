package com.bfaa.favoriteapp.repositories

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bfaa.favoriteapp.model.StateLoading
import com.bfaa.favoriteapp.model.UserGithub
import com.bfaa.favoriteapp.util.USER_CONTENT_URI
import com.bfaa.favoriteapp.util.toListUserEntity
import com.bfaa.favoriteapp.util.toUserGithub
import javax.inject.Inject


class UserRepository @Inject constructor() {
    fun checkFavorite(userId: Int, context: Context): LiveData<StateLoading<UserGithub>> {
        val liveData = MutableLiveData<StateLoading<UserGithub>>()

        val uri = "$USER_CONTENT_URI/$userId".toUri()
        val cursor = context.contentResolver
            .query(uri, null, null, null, null)

        cursor?.let {
            if (cursor.moveToFirst()) {
                liveData.postValue(StateLoading.success(it.toUserGithub()))
            } else {
                liveData.postValue(StateLoading.error("Error", null))
            }

            cursor.close()
        }

        return liveData
    }

    fun getUserFavorite(context: Context): LiveData<StateLoading<List<UserGithub>>> {
        val liveData = MutableLiveData<StateLoading<List<UserGithub>>>()

        val cursor = context.contentResolver
            .query(USER_CONTENT_URI.toUri(), null, null, null, null)
        cursor?.let {
            liveData.postValue(StateLoading.success(it.toListUserEntity()))
            cursor.close()
        }
        return liveData
    }
}
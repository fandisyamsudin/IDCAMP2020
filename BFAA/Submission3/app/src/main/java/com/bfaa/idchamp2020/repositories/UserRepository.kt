package com.bfaa.idchamp2020.repositories

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bfaa.idchamp2020.model.StateLoading
import com.bfaa.idchamp2020.model.UserGithub
import com.bfaa.idchamp2020.util.USER_CONTENT_URI
import com.bfaa.idchamp2020.util.cursorToUserGithub
import com.bfaa.idchamp2020.util.toContentValues
import com.bfaa.idchamp2020.util.toListUserEntity
import javax.inject.Inject


class UserRepository @Inject constructor() {
    fun checkFavorite(userId: Int, context: Context): LiveData<StateLoading<UserGithub>> {
        val liveData = MutableLiveData<StateLoading<UserGithub>>()

        val uri = "$USER_CONTENT_URI/$userId".toUri()
        val cursor = context.contentResolver
            .query(uri, null, null, null, null)

        cursor?.let {
            if (cursor.moveToFirst()) {
                liveData.postValue(StateLoading.success(it.cursorToUserGithub()))
            } else {
                liveData.postValue(StateLoading.error("Error", null))
            }

            cursor.close()
        }

        return liveData
    }

    fun addFavorite(user: UserGithub, context: Context): LiveData<Long> {
        val liveData = MutableLiveData<Long>()

        val cursor =
            context.contentResolver.insert(USER_CONTENT_URI.toUri(), user.toContentValues())
        cursor?.let { liveData.postValue(1) }
        return liveData
    }

    fun deleteFavorite(user: UserGithub, context: Context): LiveData<Long> {
        val liveData = MutableLiveData<Long>()

        val uri = "$USER_CONTENT_URI/${user.id}".toUri()
        val cursor =
            context.contentResolver.delete(uri, null, null)

        cursor.let { liveData.postValue(it.toLong()) }

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
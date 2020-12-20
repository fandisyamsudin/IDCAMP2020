package com.bfaa.favoriteapp.util

import android.database.Cursor
import com.bfaa.favoriteapp.model.UserGithub
import java.util.*

fun Cursor.toListUserEntity(): ArrayList<UserGithub> {
    val userEntityList = ArrayList<UserGithub>()

    apply {
        while (moveToNext()) {
            userEntityList.add(
               toUserGithub()
            )
        }
    }
    return userEntityList
}
fun Cursor.toUserGithub(): UserGithub =
    UserGithub(
        getInt(getColumnIndexOrThrow(USER_ID)),
        getInt(getColumnIndexOrThrow(USER_FOLLOWERS)),
        getInt(getColumnIndexOrThrow(USER_FOLLOWING)),
        getString(getColumnIndexOrThrow(USER_AVATAR_URL)),
        getString(getColumnIndexOrThrow(USER_FOLLOWERS_URL)),
        getString(getColumnIndexOrThrow(USER_FOLLOWING_URL)),
        getString(getColumnIndexOrThrow(USER_LOGIN)),
        getString(getColumnIndexOrThrow(USER_TYPE)),
        getString(getColumnIndexOrThrow(USER_NAME)),
        getString(getColumnIndexOrThrow(USER_COMPANY)),
        getString(getColumnIndexOrThrow(USER_LOCATION)),
    )
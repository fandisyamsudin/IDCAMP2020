package com.bfaa.idchamp2020.util

import android.content.ContentValues
import android.database.Cursor
import com.bfaa.idchamp2020.model.UserGithub
import java.util.*


const val USER_ID = "id"
const val USER_AVATAR_URL = "avatar_url"
const val USER_COMPANY = "company"
const val USER_FOLLOWERS = "followers"
const val USER_FOLLOWERS_URL = "followers_url"
const val USER_FOLLOWING = "following"
const val USER_FOLLOWING_URL = "following_url"
const val USER_LOCATION = "location"
const val USER_LOGIN = "login"
const val USER_NAME = "name"
const val USER_TYPE = "type"

fun UserGithub.toContentValues(): ContentValues =
    ContentValues().apply {
        put(USER_ID, id)
        put(USER_FOLLOWERS, followers)
        put(USER_FOLLOWING, following)
        put(USER_AVATAR_URL, avatar_url)
        put(USER_FOLLOWERS_URL, followers_url)
        put(USER_FOLLOWING_URL, following_url)
        put(USER_LOGIN, login)
        put(USER_TYPE, type)
        put(USER_NAME, name)
        put(USER_COMPANY, company)
        put(USER_LOCATION, location)
    }

fun ContentValues.cursorToUserGithub(): UserGithub =
    UserGithub(
        id = getAsInteger(USER_ID),
        followers = getAsInteger(USER_FOLLOWERS),
        following = getAsInteger(USER_FOLLOWING),
        avatar_url = getAsString(USER_AVATAR_URL),
        followers_url = getAsString(USER_FOLLOWERS_URL),
        following_url = getAsString(USER_FOLLOWING_URL),
        login = getAsString(USER_LOGIN),
        type = getAsString(USER_TYPE),
        name = getAsString(USER_NAME),
        company = getAsString(USER_COMPANY),
        location = getAsString(USER_LOCATION)
    )

fun Cursor.toListUserEntity(): ArrayList<UserGithub> {
    val userEntityList = ArrayList<UserGithub>()

    apply {
        while (moveToNext()) {
            userEntityList.add(
                cursorToUserGithub()
            )
        }
    }
    return userEntityList
}

fun Cursor.cursorToUserGithub(): UserGithub =
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
package com.bfaa.idchamp2020.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserGithub (
    val id: Int,
    val followers: Int,
    val following: Int,
    val avatar_url: String?,
    val followers_url: String?,
    val following_url: String?,
    val login: String,
    val type: String?,
    val name: String?,
    val company: String?,
    val location: String?
) : Parcelable
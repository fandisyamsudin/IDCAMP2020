package com.bfaa.idchamp2020.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bfaa.idchamp2020.util.USER_TABLE_NAME
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = USER_TABLE_NAME)
data class UserGithub (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "followers")
    val followers: Int,
    @ColumnInfo(name = "following")
    val following: Int,
    @ColumnInfo(name = "avatar_url")
    val avatar_url: String?,
    @ColumnInfo(name = "followers_url")
    val followers_url: String?,
    @ColumnInfo(name = "following_url")
    val following_url: String?,
    @ColumnInfo(name = "login")
    val login: String,
    @ColumnInfo(name = "type")
    val type: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "company")
    val company: String?,
    @ColumnInfo(name = "location")
    val location: String?
) : Parcelable
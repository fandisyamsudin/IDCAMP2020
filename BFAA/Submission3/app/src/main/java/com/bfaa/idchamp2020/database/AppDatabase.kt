package com.bfaa.idchamp2020.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bfaa.idchamp2020.model.UserGithub

@Database(
    version = 1,
    entities = [UserGithub::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}
package com.idcamp2020.made.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idcamp2020.made.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
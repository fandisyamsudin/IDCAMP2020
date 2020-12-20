package com.bfaa.idchamp2020.database

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bfaa.idchamp2020.model.UserGithub

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userGithub: UserGithub): Long

    @Query("DELETE FROM users_favorite WHERE id = :userId")
    fun deleteUser(userId: Int): Int

    @Query("SELECT * FROM users_favorite ORDER BY name ASC")
    fun getUsers(): Cursor

    @Query("SELECT * FROM users_favorite WHERE id = :userId")
    fun getUserById(userId: Int): Cursor
}
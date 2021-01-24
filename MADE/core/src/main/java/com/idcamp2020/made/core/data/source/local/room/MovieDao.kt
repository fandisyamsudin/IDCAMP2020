package com.idcamp2020.made.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.idcamp2020.made.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM table_favorite")
    fun getMovieEntity(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM table_favorite WHERE is_favorite = 1")
    fun getMovieFavorite(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieFavorite(movies: List<MovieEntity>)

    @Update
    fun updateMovieFavorite(movie: MovieEntity)

    @Query("SELECT * FROM table_favorite WHERE title LIKE '%' || :query || '%'")
    fun getMovieSearch(query: String): Flow<List<MovieEntity>>
}
package com.idcamp2020.made.core.data.source.remote.response

import android.util.Log
import com.idcamp2020.made.core.BuildConfig
import com.idcamp2020.made.core.data.source.remote.network.ApiResponse
import com.idcamp2020.made.core.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieResponse(BuildConfig.API_KEY, "en-US", 2)
                val movieArray = response.results
                if (movieArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
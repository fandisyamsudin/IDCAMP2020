package com.idcamp2020.made.core.data.source.remote.network

import com.idcamp2020.made.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovieResponse(
            @Query("api_key") apiKey: String,
    ): ListMovieResponse
}
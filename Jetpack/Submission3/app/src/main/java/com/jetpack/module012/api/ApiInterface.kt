package com.jetpack.module012.api

import com.jetpack.module012.BuildConfig
import com.jetpack.module012.data.model.Movie
import com.jetpack.module012.data.model.TVShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/now_playing")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<DataResponse<Movie>>

    @GET("tv/on_the_air")
    fun getTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<DataResponse<TVShow>>
}
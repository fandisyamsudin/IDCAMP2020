package com.jetpack.module012.api

import com.jetpack.module012.BuildConfig
import com.jetpack.module012.data.DataResponse
import com.jetpack.module012.data.Movie
import com.jetpack.module012.data.TVShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/now_playing")
    fun getMovies(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<DataResponse<Movie>>

    @GET("movie/{movie_id}")
    fun getDetailMovies(
            @Path("movie_id") movieId: Int,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<Movie>

    @GET("tv/on_the_air")
    fun getTvShows(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<DataResponse<TVShow>>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
            @Path("tv_id") tvShowId: Int,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<TVShow>
}
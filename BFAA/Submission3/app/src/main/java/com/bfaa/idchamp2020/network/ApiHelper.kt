package com.bfaa.idchamp2020.network

import com.bfaa.idchamp2020.BuildConfig
import com.bfaa.idchamp2020.model.SearchResponses
import com.bfaa.idchamp2020.model.UserGithub
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiHelper {
    companion object {
        private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        fun create(): ApiHelper {
            val okHttp = OkHttpClient.Builder()
                .addInterceptor(ApiInterceptor(BuildConfig.GITHUB_TOKEN))
                .addInterceptor(logger)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttp)
                .build()
                .create(ApiHelper::class.java)
        }
    }

    @GET("search/users")
    fun getSearchUser(
        @Query("q") keyword: String
    ): Observable<SearchResponses>

    @GET("users/{username}")
    fun getUser(
        @Path("username") username: String
    ): Observable<UserGithub>

    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String
    ): Observable<List<UserGithub>>

    @GET("users/{username}/following")
     fun getUserFollowing(
        @Path("username") username: String
    ): Observable<List<UserGithub>>
}

class ApiInterceptor(private var token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val basicReq = req.newBuilder()
            .addHeader("Authorization", token)
            .build()

        return chain.proceed(basicReq)
    }
}
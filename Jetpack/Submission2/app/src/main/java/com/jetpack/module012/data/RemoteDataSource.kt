package com.jetpack.module012.data

import com.jetpack.module012.api.ApiInterface
import com.jetpack.module012.utils.EspressoIdlingResource
import retrofit2.await
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getMovies(
            callback: LoadNowPlayingMoviesCallback
    ) {
        EspressoIdlingResource.increment()
        apiInterface.getMovies().await().results?.let { listMovie ->
            callback.allMovies(
                    listMovie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getDetailMovies(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        apiInterface.getDetailMovies(movieId).await().let { movie ->
            callback.allDetailMovies(
                    movie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShows(callback: LoadOnTheAirTvShowCallback) {
        EspressoIdlingResource.increment()
        apiInterface.getTvShows().await().results?.let { listTvShow ->
            callback.allTVShows(
                    listTvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        apiInterface.getDetailTvShow(tvShowId).await().let { tvShow ->
            callback.allTVShows(
                    tvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadNowPlayingMoviesCallback {
        fun allMovies(movieResponse: List<Movie>)
    }

    interface LoadMovieDetailCallback {
        fun allDetailMovies(movieResponse: Movie)
    }

    interface LoadOnTheAirTvShowCallback {
        fun allTVShows(tvShowResponse: List<TVShow>)
    }

    interface LoadTvShowDetailCallback {
        fun allTVShows(tvShowResponse: TVShow)
    }
}
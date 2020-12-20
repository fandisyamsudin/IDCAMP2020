package com.jetpack.module012.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
        CatalogDataSource {

    override fun getMovies(): LiveData<List<Movie>> {
        val listMovieResult = MutableLiveData<List<Movie>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMovies(object : RemoteDataSource.LoadNowPlayingMoviesCallback {
                override fun allMovies(movieResponse: List<Movie>) {
                    val movieList = ArrayList<Movie>()
                    for (response in movieResponse) {
                        val movie = Movie(
                                response.id,
                                response.title,
                                response.score,
                                response.release,
                                response.language,
                                response.overview,
                                response.poster,
                                response.backdrop
                        )
                        movieList.add(movie)
                    }
                    listMovieResult.postValue(movieList)
                }
            })
        }
        return listMovieResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<Movie> {
        val movieResult = MutableLiveData<Movie>()
        CoroutineScope(IO).launch {
            remoteDataSource.getDetailMovies(
                    movieId,
                    object : RemoteDataSource.LoadMovieDetailCallback {
                        override fun allDetailMovies(movieResponse: Movie) {
                            val movie = Movie(
                                    movieResponse.id,
                                    movieResponse.title,
                                    movieResponse.score,
                                    movieResponse.release,
                                    movieResponse.language,
                                    movieResponse.overview,
                                    movieResponse.poster,
                                    movieResponse.backdrop
                            )
                            movieResult.postValue(movie)
                        }
                    })
        }
        return movieResult
    }

    override fun getTVShow(): LiveData<List<TVShow>> {
        val listTvShowResult = MutableLiveData<List<TVShow>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShows(object : RemoteDataSource.LoadOnTheAirTvShowCallback {
                override fun allTVShows(tvShowResponse: List<TVShow>) {
                    val tvShowList = ArrayList<TVShow>()
                    for (response in tvShowResponse) {
                        val tvShow = TVShow(
                                response.id,
                                response.title,
                                response.score,
                                response.release,
                                response.language,
                                response.overview,
                                response.poster,
                                response.backdrop
                        )
                        tvShowList.add(tvShow)
                    }
                    listTvShowResult.postValue(tvShowList)
                }
            })
        }
        return listTvShowResult
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<TVShow> {
        val tvShowResult = MutableLiveData<TVShow>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShowDetail(
                    tvShowId,
                    object : RemoteDataSource.LoadTvShowDetailCallback {
                        override fun allTVShows(tvShowResponse: TVShow) {
                            val tvShow = TVShow(
                                    tvShowResponse.id,
                                    tvShowResponse.title,
                                    tvShowResponse.score,
                                    tvShowResponse.release,
                                    tvShowResponse.language,
                                    tvShowResponse.overview,
                                    tvShowResponse.poster,
                                    tvShowResponse.backdrop
                            )
                            tvShowResult.postValue(tvShow)
                        }
                    })
        }
        return tvShowResult
    }
}

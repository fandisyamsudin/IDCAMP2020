package com.jetpack.module012.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jetpack.module012.data.local.CatalogDataSource
import com.jetpack.module012.data.local.LocalDataSource
import com.jetpack.module012.data.model.Movie
import com.jetpack.module012.data.model.TVShow
import com.jetpack.module012.data.remote.RemoteDataSource
import com.jetpack.module012.data.room.MovieEntity
import com.jetpack.module012.data.room.TVShowEntity
import com.jetpack.module012.utils.NetworkBoundResource
import com.jetpack.module012.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CatalogDataSource {

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<Resource<List<Movie>>> {
                return remoteDataSource.getMovies()
            }

            override fun saveCallResult(data: List<Movie>) {
                val listMovie = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.score,
                        response.release,
                        response.language,
                        response.overview,
                        response.poster,
                        response.backdrop,
                        false
                    )
                    listMovie.add(movie)
                }
                localDataSource.insertFavoriteMovie(listMovie)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getMovieById(id: Int): LiveData<MovieEntity> {
        return localDataSource.getMovieById(id)
    }

    override fun getTVShow(): LiveData<Resource<PagedList<TVShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TVShowEntity>, List<TVShow>>() {
            override fun loadFromDB(): LiveData<PagedList<TVShowEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getTVShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TVShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<Resource<List<TVShow>>> {
                return remoteDataSource.getTVShows()
            }

            override fun saveCallResult(data: List<TVShow>) {
                val listTV = ArrayList<TVShowEntity>()
                for (response in data) {
                    val tvShow = TVShowEntity(
                        response.id,
                        response.title,
                        response.score,
                        response.release,
                        response.language,
                        response.overview,
                        response.poster,
                        response.backdrop,
                        false
                    )
                    listTV.add(tvShow)
                }
                localDataSource.insertFavoriteTV(listTV)
            }
        }.asLiveData()
    }

    override fun getFavoriteTV(): LiveData<PagedList<TVShowEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteTV(), config).build()
    }

    override fun getTVById(id: Int): LiveData<TVShowEntity> {
        return localDataSource.getTVById(id)
    }

    override fun setFavoriteMovie(movie: MovieEntity) {
        CoroutineScope(IO).launch {
            localDataSource.setFavoriteMovie(movie)
        }
    }

    override fun setFavoriteTV(tvShow: TVShowEntity) {
        CoroutineScope(IO).launch {
            localDataSource.setFavoriteTV(tvShow)
        }
    }
}

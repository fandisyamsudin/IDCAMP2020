package com.idcamp2020.made.core.data

import com.idcamp2020.made.core.data.source.local.LocalDataSource
import com.idcamp2020.made.core.data.source.remote.network.ApiResponse
import com.idcamp2020.made.core.data.source.remote.response.MovieResponse
import com.idcamp2020.made.core.data.source.remote.response.RemoteDataSource
import com.idcamp2020.made.core.domain.model.Movie
import com.idcamp2020.made.core.domain.repository.IMovieRepository
import com.idcamp2020.made.core.utils.AppExecutors
import com.idcamp2020.made.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {
    override fun getMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovieFavorite(movieList)
            }
        }.asFlow()

    override fun getMovieFavorite(): Flow<List<Movie>> {
        return localDataSource.getAllMovieFavorite().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getMovieSearch(query: String): Flow<List<Movie>> {
        return localDataSource.getMovieSearch(query).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, state) }
    }
}
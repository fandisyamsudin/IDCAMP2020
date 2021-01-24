package com.idcamp2020.made.core.domain.usecase

import com.idcamp2020.made.core.data.Resource
import com.idcamp2020.made.core.domain.model.Movie
import com.idcamp2020.made.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val iMovieRepository: IMovieRepository) : MovieUseCase {
    override fun getMovie(): Flow<Resource<List<Movie>>> {
        return iMovieRepository.getMovie()
    }

    override fun getMovieFavorite(): Flow<List<Movie>> {
        return iMovieRepository.getMovieFavorite()
    }

    override fun getMovieSearch(query: String): Flow<List<Movie>> {
        return iMovieRepository.getMovieSearch(query)
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        return iMovieRepository.setMovieFavorite(movie, state)
    }
}
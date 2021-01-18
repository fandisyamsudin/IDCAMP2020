package com.idcamp2020.made.core.utils

import com.idcamp2020.made.core.data.source.local.entity.MovieEntity
import com.idcamp2020.made.core.data.source.remote.response.MovieResponse
import com.idcamp2020.made.core.domain.model.Movie

object DataMapper {
    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                it.id,
                it.voteAverage,
                it.title,
                it.releaseDate,
                it.overview,
                it.posterPath,
                it.backdropPath,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map {
            Movie(
                it.id,
                it.voteAverage,
                it.title,
                it.releaseDate,
                it.overview,
                it.posterPath,
                it.backdropPath,
                isFavorite = false
            )
        }
    }

    fun mapDomainToEntity(input: Movie): MovieEntity{
        return MovieEntity(
            input.id,
            input.voteAverage,
            input.title,
            input.releaseDate,
            input.overview,
            input.posterPath,
            input.backdropPath,
            isFavorite = input.isFavorite
        )
    }
}
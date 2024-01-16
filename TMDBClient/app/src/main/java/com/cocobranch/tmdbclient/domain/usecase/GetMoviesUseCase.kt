package com.cocobranch.tmdbclient.domain.usecase

import com.cocobranch.tmdbclient.data.model.movie.Movie
import com.cocobranch.tmdbclient.domain.repository.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.getMovies()
}
package com.cocobranch.tmdbclient.domain.repository
import com.cocobranch.tmdbclient.data.model.movie.Movie

interface MovieRepository {
    suspend fun getMovies(): List<Movie>?
    suspend fun updateMovies():List<Movie>?
}
package com.cocobranch.tmdbclient.data.repository.movie.datasource

import com.cocobranch.tmdbclient.data.model.movie.Movie

interface MovieCacheDataSource {
    suspend fun getMoviesFromCache(): List<Movie>
    suspend fun saveMoviesToCache(movies: List<Movie>)
}
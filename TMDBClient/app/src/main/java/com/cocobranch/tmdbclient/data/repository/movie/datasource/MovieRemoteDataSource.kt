package com.cocobranch.tmdbclient.data.repository.movie.datasource

import com.cocobranch.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies(): Response<MovieList>
}
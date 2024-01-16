package com.cocobranch.tmdbclient.data.repository.movie.datasourceImpl

import com.cocobranch.tmdbclient.data.api.TMDBService
import com.cocobranch.tmdbclient.data.model.movie.MovieList
import com.cocobranch.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String): MovieRemoteDataSource {
    override suspend fun getMovies(): Response<MovieList> {
        return tmdbService.getPopularMovies(apiKey)
    }

}
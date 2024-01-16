package com.cocobranch.tmdbclient.data.repository.tvShow.datasourceImpl

import com.cocobranch.tmdbclient.data.api.TMDBService
import com.cocobranch.tmdbclient.data.model.tvShow.TvShowList
import com.cocobranch.tmdbclient.data.repository.tvShow.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String): TvShowRemoteDataSource {
    override suspend fun getTvShows(): Response<TvShowList> {
        return tmdbService.getPopularTvShow(apiKey)
    }
}
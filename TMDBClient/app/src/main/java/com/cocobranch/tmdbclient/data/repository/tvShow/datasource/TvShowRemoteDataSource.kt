package com.cocobranch.tmdbclient.data.repository.tvShow.datasource

import com.cocobranch.tmdbclient.data.model.tvShow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShows(): Response<TvShowList>
}
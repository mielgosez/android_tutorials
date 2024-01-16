package com.cocobranch.tmdbclient.data.repository.tvShow.datasource

import com.cocobranch.tmdbclient.data.model.tvShow.TvShow

interface TvShowCacheDataSource {
    suspend fun getTvShowsFromCache(): List<TvShow>
    suspend fun saveTvShowsToCache(tvShows: List<TvShow>)
}
package com.cocobranch.tmdbclient.data.repository.tvShow.datasourceImpl

import com.cocobranch.tmdbclient.data.model.tvShow.TvShow
import com.cocobranch.tmdbclient.data.repository.tvShow.datasource.TvShowCacheDataSource

class TvShowCacheDataSourceImpl: TvShowCacheDataSource {
    private var tvShowlist = ArrayList<TvShow>()
    
    override suspend fun getTvShowsFromCache(): List<TvShow> {
        return tvShowlist
    }

    override suspend fun saveTvShowsToCache(tvShows: List<TvShow>) {
        tvShowlist.clear()
        tvShowlist = ArrayList(tvShows)
    }
}
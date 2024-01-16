package com.cocobranch.tmdbclient.data.repository.tvShow.datasource

import com.cocobranch.tmdbclient.data.model.tvShow.TvShow

interface TvShowLocalDataSource {
    suspend fun getTvShowsFromDB(): List<TvShow>
    suspend fun saveTvShowsToDB(tvShows: List<TvShow>)
    suspend fun clearAll()
}
package com.cocobranch.tmdbclient.domain.repository
import com.cocobranch.tmdbclient.data.model.tvShow.TvShow

interface TvShowRepository {
    suspend fun getTvShows(): List<TvShow>?
    suspend fun updateTvShows():List<TvShow>?
}
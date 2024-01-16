package com.cocobranch.tmdbclient.data.repository.tvShow.datasourceImpl

import com.cocobranch.tmdbclient.data.db.TvShowDao
import com.cocobranch.tmdbclient.data.model.tvShow.TvShow
import com.cocobranch.tmdbclient.data.repository.tvShow.datasource.TvShowLocalDataSource

class TvShowLocalDataSourceImpl(
    private val tvShowDao: TvShowDao
): TvShowLocalDataSource {
    override suspend fun getTvShowsFromDB(): List<TvShow> {
        return tvShowDao.getTvShows()
    }

    override suspend fun saveTvShowsToDB(tvShows: List<TvShow>) {
        return tvShowDao.saveTvShows(tvShows)
    }

    override suspend fun clearAll() {
        tvShowDao.deleteAllTvShows()
    }

}
package com.cocobranch.tmdbclient.data.repository.tvShow

import android.util.Log
import com.cocobranch.tmdbclient.data.model.tvShow.TvShow
import com.cocobranch.tmdbclient.data.repository.tvShow.datasource.TvShowCacheDataSource
import com.cocobranch.tmdbclient.data.repository.tvShow.datasource.TvShowLocalDataSource
import com.cocobranch.tmdbclient.data.repository.tvShow.datasource.TvShowRemoteDataSource
import com.cocobranch.tmdbclient.domain.repository.TvShowRepository
import java.lang.Exception

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
): TvShowRepository {
    override suspend fun getTvShows(): List<TvShow>? {
        return getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {
        val tvShows = getTvShowsFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDB(tvShows)
        tvShowCacheDataSource.saveTvShowsToCache(tvShows)
        return tvShows
    }

    suspend fun getTvShowsFromDB(): List<TvShow>{
        lateinit var tvShows: List<TvShow>
        try {
            tvShows = tvShowLocalDataSource.getTvShowsFromDB()
        }catch (exception: Exception){
            Log.i("My Tag", exception.message.toString())
        }
        if (tvShows.isNotEmpty()){
            return tvShows
        }else{
            tvShows = getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDB(tvShows)
        }
        return tvShows
    }

    suspend fun getTvShowsFromAPI(): List<TvShow>{
        lateinit var tvShows: List<TvShow>
        try {
            var response = tvShowRemoteDataSource.getTvShows()
            var body = response.body()
            if (body!=null){
                tvShows = body.tvShows
            }
        }catch (exception: Exception){
            Log.i("My Tag", exception.message.toString())
        }
        return tvShows
    }

    suspend fun getTvShowsFromCache(): List<TvShow>{
        lateinit var tvShow: List<TvShow>
        try {
            tvShow = tvShowCacheDataSource.getTvShowsFromCache()
        }catch (exception: Exception){
            Log.i("My Tag", exception.message.toString())
        }
        if(tvShow.isNotEmpty()){
            return tvShow
        }else{
            tvShow=getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShow)
        }
        return tvShow
    }
}
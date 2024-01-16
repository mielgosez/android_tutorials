package com.cocobranch.tmdbclient.presentation.di.core

import com.cocobranch.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.cocobranch.tmdbclient.data.repository.artist.datasourceImpl.ArtistCacheDataSourceImpl
import com.cocobranch.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.cocobranch.tmdbclient.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import com.cocobranch.tmdbclient.data.repository.movie.datasourceImpl.MovieLocalDataSourceImpl
import com.cocobranch.tmdbclient.data.repository.tvShow.datasource.TvShowCacheDataSource
import com.cocobranch.tmdbclient.data.repository.tvShow.datasourceImpl.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun provideMovieCacheDataSource():MovieCacheDataSource{
        return MovieCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideTvShowCacheDataSource():TvShowCacheDataSource{
        return TvShowCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideArtistCacheDataSource():ArtistCacheDataSource{
        return ArtistCacheDataSourceImpl()
    }
}
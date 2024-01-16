package com.cocobranch.tmdbclient.presentation.di.core

import com.cocobranch.tmdbclient.data.db.ArtistDao
import com.cocobranch.tmdbclient.data.db.MovieDao
import com.cocobranch.tmdbclient.data.db.TvShowDao
import com.cocobranch.tmdbclient.data.model.tvShow.TvShow
import com.cocobranch.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.cocobranch.tmdbclient.data.repository.artist.datasourceImpl.ArtistLocalDataSourceImpl
import com.cocobranch.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.cocobranch.tmdbclient.data.repository.movie.datasourceImpl.MovieLocalDataSourceImpl
import com.cocobranch.tmdbclient.data.repository.tvShow.datasource.TvShowLocalDataSource
import com.cocobranch.tmdbclient.data.repository.tvShow.datasourceImpl.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource{
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun provideTvShowLocalDataSource(tvShowDao: TvShowDao): TvShowLocalDataSource {
        return TvShowLocalDataSourceImpl(tvShowDao)
    }

    @Singleton
    @Provides
    fun provideArtistLocalDataSource(artistDao: ArtistDao): ArtistLocalDataSource {
        return ArtistLocalDataSourceImpl(artistDao)
    }
}
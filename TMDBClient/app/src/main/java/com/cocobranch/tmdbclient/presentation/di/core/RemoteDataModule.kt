package com.cocobranch.tmdbclient.presentation.di.core

import com.cocobranch.tmdbclient.data.api.TMDBService
import com.cocobranch.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.cocobranch.tmdbclient.data.repository.artist.datasourceImpl.ArtistRemoteDataSourceImpl
import com.cocobranch.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.cocobranch.tmdbclient.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.cocobranch.tmdbclient.data.repository.tvShow.datasource.TvShowRemoteDataSource
import com.cocobranch.tmdbclient.data.repository.tvShow.datasourceImpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(
    private val apiKey: String
) {

    @Singleton
    @Provides
    fun providemMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDataSource{
        return MovieRemoteDataSourceImpl(
            tmdbService, apiKey
        )
    }

    @Singleton
    @Provides
    fun providemArtistRemoteDataSource(tmdbService: TMDBService): ArtistRemoteDataSource {
        return ArtistRemoteDataSourceImpl(
            tmdbService, apiKey
        )
    }

    @Singleton
    @Provides
    fun providemTvRemoteDataSource(tmdbService: TMDBService): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(
            tmdbService, apiKey
        )
    }
}
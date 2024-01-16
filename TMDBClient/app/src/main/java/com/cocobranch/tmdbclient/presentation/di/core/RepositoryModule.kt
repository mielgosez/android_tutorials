package com.cocobranch.tmdbclient.presentation.di.core

import com.cocobranch.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.cocobranch.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.cocobranch.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.cocobranch.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.cocobranch.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.cocobranch.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.cocobranch.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.cocobranch.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.cocobranch.tmdbclient.data.repository.tvShow.TvShowRepositoryImpl
import com.cocobranch.tmdbclient.data.repository.tvShow.datasource.TvShowCacheDataSource
import com.cocobranch.tmdbclient.data.repository.tvShow.datasource.TvShowLocalDataSource
import com.cocobranch.tmdbclient.data.repository.tvShow.datasource.TvShowRemoteDataSource
import com.cocobranch.tmdbclient.domain.repository.ArtistRepository
import com.cocobranch.tmdbclient.domain.repository.MovieRepository
import com.cocobranch.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository{
        return MovieRepositoryImpl(
            movieRemoteDataSource,
            movieLocalDataSource,
            movieCacheDataSource
        )
    }

    @Singleton
    @Provides
    fun provideArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource,
        artistLocalDataSource: ArtistLocalDataSource,
        artistCacheDataSource: ArtistCacheDataSource
    ): ArtistRepository {
        return ArtistRepositoryImpl(
            artistLocalDataSource,
            artistRemoteDataSource,
            artistCacheDataSource
        )
    }

    @Singleton
    @Provides
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ): TvShowRepository {
        return TvShowRepositoryImpl(
            tvShowRemoteDataSource,
            tvShowLocalDataSource,
            tvShowCacheDataSource
        )
    }
}
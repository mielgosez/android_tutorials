package com.cocobranch.tmdbclient.presentation.di.tvshow

import com.cocobranch.tmdbclient.domain.usecase.GetArtistsUseCase
import com.cocobranch.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.cocobranch.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.cocobranch.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import com.cocobranch.tmdbclient.presentation.artist.ArtistViewModelFactory
import com.cocobranch.tmdbclient.presentation.tvShow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {
    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(
            getTvShowsUseCase, updateTvShowsUseCase
        )
    }
}
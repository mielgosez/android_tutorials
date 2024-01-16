package com.cocobranch.tmdbclient.presentation.di.artist

import com.cocobranch.tmdbclient.domain.usecase.GetArtistsUseCase
import com.cocobranch.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.cocobranch.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {
    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistViewModelFactory{
        return ArtistViewModelFactory(
            getArtistsUseCase, updateArtistsUseCase
        )
    }
}
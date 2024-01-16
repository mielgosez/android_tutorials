package com.cocobranch.tmdbclient.presentation.di.movie

import com.cocobranch.tmdbclient.domain.usecase.GetArtistsUseCase
import com.cocobranch.tmdbclient.domain.usecase.GetMoviesUseCase
import com.cocobranch.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.cocobranch.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.cocobranch.tmdbclient.presentation.artist.ArtistViewModelFactory
import com.cocobranch.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {
    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): MovieViewModelFactory{
        return MovieViewModelFactory(
            getMoviesUseCase, updateMoviesUseCase
        )
    }
}
package com.cocobranch.tmdbclient.presentation.di

import com.cocobranch.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.cocobranch.tmdbclient.presentation.di.movie.MovieSubComponent
import com.cocobranch.tmdbclient.presentation.di.tvshow.TvShowSubComponent

interface Injector {
    fun createMovieSubComponent(): MovieSubComponent
    fun createTvShowSubComponent(): TvShowSubComponent
    fun createArtistSubComponent(): ArtistSubComponent
}
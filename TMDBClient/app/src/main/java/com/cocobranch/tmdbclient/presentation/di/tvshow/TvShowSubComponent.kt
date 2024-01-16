package com.cocobranch.tmdbclient.presentation.di.tvshow

import com.cocobranch.tmdbclient.presentation.artist.ArtistActivity
import com.cocobranch.tmdbclient.presentation.tvShow.TvShowActivity
import dagger.Subcomponent

@TvShowScope
@Subcomponent(modules = [TvShowModule::class])
interface TvShowSubComponent {
    fun inject(tvShowActivity: TvShowActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create(): TvShowSubComponent
    }
}
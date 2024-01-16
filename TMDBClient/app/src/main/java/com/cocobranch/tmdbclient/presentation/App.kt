package com.cocobranch.tmdbclient.presentation

import android.app.Application
import com.cocobranch.tmdbclient.BuildConfig
import com.cocobranch.tmdbclient.presentation.di.Injector
import com.cocobranch.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.cocobranch.tmdbclient.presentation.di.core.AppComponent
import com.cocobranch.tmdbclient.presentation.di.core.AppModule
import com.cocobranch.tmdbclient.presentation.di.core.DaggerAppComponent
import com.cocobranch.tmdbclient.presentation.di.core.NetModule
import com.cocobranch.tmdbclient.presentation.di.core.RemoteDataModule
import com.cocobranch.tmdbclient.presentation.di.movie.MovieSubComponent
import com.cocobranch.tmdbclient.presentation.di.tvshow.TvShowSubComponent

class App: Application(), Injector {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()
    }

    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }

    override fun createTvShowSubComponent(): TvShowSubComponent {
        return appComponent.tvShowSubComponent().create()
    }

    override fun createArtistSubComponent(): ArtistSubComponent {
        return appComponent.artistSubComponent().create()
    }

}
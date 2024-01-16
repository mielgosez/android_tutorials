package com.cocobranch.tmdbclient.presentation.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.cocobranch.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.cocobranch.tmdbclient.domain.usecase.UpdateTvShowsUseCase

class TvShowViewModel(
    private var getTvShowsUseCase: GetTvShowsUseCase,
    private var updateTvShowsUseCase: UpdateTvShowsUseCase
): ViewModel() {
    fun getTvShow() = liveData {
        val tvShowList = getTvShowsUseCase.execute()
        emit(tvShowList)
    }

    fun updateTvShow() = liveData {
        val tvShowList = updateTvShowsUseCase.execute()
        emit(tvShowList)
    }
}
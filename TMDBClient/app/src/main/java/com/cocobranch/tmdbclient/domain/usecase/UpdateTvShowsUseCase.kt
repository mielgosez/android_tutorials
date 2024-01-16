package com.cocobranch.tmdbclient.domain.usecase

import com.cocobranch.tmdbclient.data.model.tvShow.TvShow
import com.cocobranch.tmdbclient.domain.repository.TvShowRepository

class UpdateTvShowsUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute(): List<TvShow>? = tvShowRepository.updateTvShows()
}
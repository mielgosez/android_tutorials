package com.cocobranch.tmdbclient.domain.usecase

import com.cocobranch.tmdbclient.data.model.artist.Artist
import com.cocobranch.tmdbclient.domain.repository.ArtistRepository

class GetArtistsUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute(): List<Artist>? = artistRepository.getArtists()
}
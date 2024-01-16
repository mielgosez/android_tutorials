package com.cocobranch.tmdbclient.data.repository.artist.datasource

import com.cocobranch.tmdbclient.data.model.artist.Artist

interface ArtistCacheDataSource {
    suspend fun getArtistFromCache(): List<Artist>
    suspend fun saveArtistsToCache(artists: List<Artist>)
}
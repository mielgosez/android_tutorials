package com.cocobranch.tmdbclient.data.repository.artist.datasource

import com.cocobranch.tmdbclient.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtists(): Response<ArtistList>
}
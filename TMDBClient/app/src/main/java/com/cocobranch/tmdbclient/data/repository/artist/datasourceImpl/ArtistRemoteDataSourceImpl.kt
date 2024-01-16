package com.cocobranch.tmdbclient.data.repository.artist.datasourceImpl

import com.cocobranch.tmdbclient.data.api.TMDBService
import com.cocobranch.tmdbclient.data.model.artist.ArtistList
import com.cocobranch.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
): ArtistRemoteDataSource {
    override suspend fun getArtists(): Response<ArtistList> {
        return tmdbService.getPopularArtist(apiKey)
    }
}
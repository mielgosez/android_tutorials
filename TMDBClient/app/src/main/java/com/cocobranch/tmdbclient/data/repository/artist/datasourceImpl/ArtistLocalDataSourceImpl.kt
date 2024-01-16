package com.cocobranch.tmdbclient.data.repository.artist.datasourceImpl

import com.cocobranch.tmdbclient.data.db.ArtistDao
import com.cocobranch.tmdbclient.data.model.artist.Artist
import com.cocobranch.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(
    private val artistDao: ArtistDao
): ArtistLocalDataSource {
    override suspend fun getArtistsFromDB(): List<Artist> {
        return artistDao.getArtists()
    }

    override suspend fun saveArtistToDB(artist: List<Artist>) {
        CoroutineScope( Dispatchers.IO ).launch {
            artistDao.saveArtists(artist)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.deleteAllArtists()
        }
    }
}
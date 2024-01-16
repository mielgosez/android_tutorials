package com.cocobranch.tmdbclient.data.repository.artist

import android.util.Log
import com.cocobranch.tmdbclient.data.model.artist.Artist
import com.cocobranch.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.cocobranch.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.cocobranch.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.cocobranch.tmdbclient.domain.repository.ArtistRepository
import java.lang.Exception

class ArtistRepositoryImpl(
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
): ArtistRepository {
    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
        val newListArtists = getArtistsFromAPI()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistToDB(newListArtists)
        artistCacheDataSource.saveArtistsToCache(newListArtists)
        return newListArtists
    }

    suspend fun getArtistsFromDB(): List<Artist>{
        lateinit var artistList: List<Artist>
        try {
            artistList = artistLocalDataSource.getArtistsFromDB()
        }catch (exception: Exception){
            Log.i("My Tag", exception.message.toString())
        }
        if(artistList.isNotEmpty()){
            return artistList
        }else{
            artistList=getArtistsFromAPI()
            artistLocalDataSource.saveArtistToDB(artistList)
        }
        return artistList
    }

    suspend fun getArtistsFromAPI(): List<Artist>{
        lateinit var artistList: List<Artist>
        try {
            val response = artistRemoteDataSource.getArtists()
            val body = response.body()
            if(body!=null){
                artistList = body.artists
            }
        }catch (exception: Exception){
            Log.i("My Tag", exception.message.toString())
        }
        return artistList
    }

    suspend fun getArtistsFromCache(): List<Artist>{
        lateinit var artistList: List<Artist>
        try {
            artistList = artistCacheDataSource.getArtistFromCache()
        }catch (exception: Exception){
            Log.i("My Tag", exception.message.toString())
        }
        if(artistList.isNotEmpty()){
            return artistList
        }else{
            artistList=getArtistsFromDB()
            artistCacheDataSource.saveArtistsToCache(artistList)
        }
        return artistList
    }
}
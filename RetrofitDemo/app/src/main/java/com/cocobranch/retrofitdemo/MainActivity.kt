package com.cocobranch.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)
        uploadAlbum(retService)
        //getRequestWithQueryParameters(retService)
        // getRequestWithPathParameters(retService)
    }

    private fun getRequestWithQueryParameters(retService: AlbumService){
        Log.i("My Tag", "Setting tv handler")
        val text_view = findViewById<TextView>(R.id.text_view)
        Log.i("My Tag", "Retrieved handler by ID")
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            Log.i("My Tag", "retrieving albums")
            val response = retService.getAlbums()
            //val response = retService.getSortedAlbums(3)
            Log.i("My Tag", "Success and emitting")
            emit(response)
        }
        responseLiveData.observe(this, {
            val albumList = it.body()?.listIterator()
            if(albumList!=null){
                var text2show: String = ""
                while(albumList.hasNext()){
                    val albumsItem = albumList.next()
                    val result=" "+"Album Title : ${albumsItem.title}"+"\n"+
                            " "+"Album id : ${albumsItem.id}"+"\n"+
                            " "+"Album user : ${albumsItem.userId}"+"\n\n\n"
                    text2show = text2show + result
                }
                text_view.text = text2show
                Log.i("My Tag", text2show)
            }
        })
    }

    private fun getRequestWithPathParameters(retService: AlbumService){
        val pathResponse : LiveData<Response<AlbumItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this, {
            val title = it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
        })
    }

    private fun uploadAlbum(retService: AlbumService){
        val album = AlbumItem(0, "My title", 3)
        val postResponse : LiveData<Response<AlbumItem>> = liveData {
            val response = retService.uploadAlbum(album)
            emit(response)
        }
        postResponse.observe(this, {
            val receivedAlbumItem = it.body()
            val result=" "+"Album Title : ${receivedAlbumItem?.title}"+"\n"+
                    " "+"Album id : ${receivedAlbumItem?.id}"+"\n"+
                    " "+"Album user : ${receivedAlbumItem?.userId}"+"\n\n\n"
            val text_view = findViewById<TextView>(R.id.text_view)
            text_view.text = result
        })
    }

}
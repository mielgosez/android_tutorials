package com.cocobranch.codingchallengecoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//main
//background
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.Main).launch {
            launchMain()
        }
        CoroutineScope(Dispatchers.IO).launch {
            launchBackground()
        }
    }
    private fun launchMain() {
            Log.i("MyTag", "This task is on ${Thread.currentThread().name}")
    }
    private fun launchBackground() {
        Log.i("MyTag", "This task is on ${Thread.currentThread().name}")
    }
}
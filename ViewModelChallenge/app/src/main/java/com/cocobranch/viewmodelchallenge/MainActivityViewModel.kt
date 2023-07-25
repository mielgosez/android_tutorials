package com.cocobranch.viewmodelchallenge

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal: Int): ViewModel() {
    private var count = MutableLiveData<Int>()
    val countData: LiveData<Int>
    get() = count
    var newValData = MutableLiveData<String>()

    init {
        count.value = startingTotal
    }

    fun addValue(){
        val newInt = newValData.value!!.toInt()
        Log.i("MyLogger", "{newInt}")
        count.value = (count.value)?.plus(newInt)
    }
}
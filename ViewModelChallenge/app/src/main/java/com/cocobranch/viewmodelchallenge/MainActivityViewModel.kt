package com.cocobranch.viewmodelchallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal: Int): ViewModel() {
    private var count = MutableLiveData<Int>()
    val countData: LiveData<Int>
    get() = count

    init {
        count.value = startingTotal
    }

    fun addValue(newVal: Int){
        count.value = (count.value)?.plus(newVal)
    }
}
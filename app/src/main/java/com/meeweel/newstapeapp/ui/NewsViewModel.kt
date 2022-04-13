package com.meeweel.newstapeapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.newstapeapp.data.NewsTapeState

class NewsViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<NewsTapeState> = MutableLiveData()
    fun getData(): LiveData<NewsTapeState> {
        return liveDataToObserve
    }

    fun getNewsFromLocalSource() = getDataFromNetworkSource()

    private fun getDataFromNetworkSource() {
        TODO()
    }
}
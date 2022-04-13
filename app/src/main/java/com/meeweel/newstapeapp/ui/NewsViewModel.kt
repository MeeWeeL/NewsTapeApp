package com.meeweel.newstapeapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.newstapeapp.data.models.NewsTapeState
import com.meeweel.newstapeapp.data.repository.NewsRepository
import com.meeweel.newstapeapp.data.repository.NewsRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsViewModel(private val repository: NewsRepository = NewsRepositoryImpl()) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<NewsTapeState> = MutableLiveData()

    fun getData(): LiveData<NewsTapeState> {
        return liveDataToObserve
    }

    fun getNewsFromLocalSource() = getDataFromNetworkSource()

    private fun getDataFromNetworkSource() {
        repository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveDataToObserve.postValue(NewsTapeState.Loading)
            }
            .subscribe({
                liveDataToObserve.postValue(NewsTapeState.Success(it))
            }, {
                liveDataToObserve.postValue(NewsTapeState.Error(Throwable("Connection error")))
            })
    }
}
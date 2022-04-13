package com.meeweel.newstapeapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.newstapeapp.data.models.NewsPostDTO
import com.meeweel.newstapeapp.data.models.NewsTapeState
import com.meeweel.newstapeapp.data.repository.NewsRepository
import com.meeweel.newstapeapp.data.repository.NewsRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsViewModel(private val repository: NewsRepository = NewsRepositoryImpl()) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<NewsTapeState> = MutableLiveData()
    private var listForLifeData: MutableList<NewsPostDTO> = mutableListOf()

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
                setLiveData(it)
            }, {
                liveDataToObserve.postValue(NewsTapeState.Error(Throwable("Connection error")))
            })
    }

    fun randomizeData() {
        val first: MutableList<NewsPostDTO> = mutableListOf()
        val second: MutableList<NewsPostDTO> = mutableListOf()
        val third: MutableList<NewsPostDTO> = mutableListOf()
        val fourth: MutableList<NewsPostDTO> = mutableListOf()
        val fifth: MutableList<NewsPostDTO> = mutableListOf()
        val newList: MutableList<NewsPostDTO> = mutableListOf()
        for (item in listForLifeData) {
            when((Math.random() * 5).toInt()) {
                0 -> first.add(item)
                1 -> second.add(item)
                2 -> third.add(item)
                3 -> fourth.add(item)
                4 -> fifth.add(item)
            }
        }
        newList.clear()
        newList.addAll(first)
        newList.addAll(second)
        newList.addAll(third)
        newList.addAll(fourth)
        newList.addAll(fifth)
        setLiveData(newList)
    }

    private fun setLiveData(list: List<NewsPostDTO>) {
        listForLifeData = list.toMutableList()
        liveDataToObserve.postValue(NewsTapeState.Success(list))
    }
}
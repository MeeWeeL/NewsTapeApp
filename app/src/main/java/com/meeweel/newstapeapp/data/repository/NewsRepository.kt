package com.meeweel.newstapeapp.data.repository

import com.meeweel.newstapeapp.data.models.NewsPostDTO
import io.reactivex.rxjava3.core.Single

interface NewsRepository {
    fun getNews() : Single<List<NewsPostDTO>>
}
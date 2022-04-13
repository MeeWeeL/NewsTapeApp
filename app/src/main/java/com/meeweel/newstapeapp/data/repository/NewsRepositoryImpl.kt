package com.meeweel.newstapeapp.data.repository

import com.meeweel.newstapeapp.data.models.Converter
import com.meeweel.newstapeapp.data.models.NewsPostDTO
import com.meeweel.newstapeapp.network.NewsApi
import com.meeweel.newstapeapp.network.NewsApiFactory
import io.reactivex.rxjava3.core.Single

class NewsRepositoryImpl(
    private val api: NewsApi = NewsApiFactory().create(),
    private val converter: Converter = Converter()
) : NewsRepository {

    override fun getNews(): Single<List<NewsPostDTO>> {
        return api.fetchNews().map {
            converter.convertResponseToNewsPostList(it)
        }
    }
}
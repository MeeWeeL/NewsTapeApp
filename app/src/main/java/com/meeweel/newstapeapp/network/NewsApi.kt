package com.meeweel.newstapeapp.network

import com.meeweel.newstapeapp.data.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "bc9b985f34934d40a6442ee42d8777b6"

interface NewsApi {

    @GET("/everything?sortBy=publishedAt")
    fun fetchNews(
        @Query("q") q: String = "all",
        @Query("from") from: String = "2022-04-13", // Запрос
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<Response>
}
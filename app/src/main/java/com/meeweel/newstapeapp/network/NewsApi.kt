package com.meeweel.newstapeapp.network

import com.meeweel.newstapeapp.data.models.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

private const val API_KEY = "bc9b985f34934d40a6442ee42d8777b6"

interface NewsApi {

    @GET("/v2/everything?sortBy=publishedAt")
    fun fetchNews(
        @Query("q") q: String = "all",
        @Query("from") from: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<Response>
}
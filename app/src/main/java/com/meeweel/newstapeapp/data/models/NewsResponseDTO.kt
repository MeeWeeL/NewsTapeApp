package com.meeweel.newstapeapp.data.models

data class NewsResponseDTO (
    val title: String,
    val description: String,
    val url: String,
    val source: SourceResponse,
    val urlToImage: String?,
    val publishedAt: String,
)

data class SourceResponse(
    val name: String
)

data class Response(
    val articles: List<NewsResponseDTO>
)
package com.meeweel.newstapeapp.data

data class NewsResponseDTO (
val title: String,
val description: String,
val url: String,
val source: SourceResponse,
val image: String,
val publishedAt: String,
)

data class SourceResponse(
    val name: String
)

data class Response(
    val news: List<NewsResponseDTO>
)
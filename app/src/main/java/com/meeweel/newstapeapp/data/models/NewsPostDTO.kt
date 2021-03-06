package com.meeweel.newstapeapp.data.models

data class NewsPostDTO(
    val title: String,
    val description: String,
    val url: String,
    val source: String,
    val image: String?,
    val published_at: String,
)
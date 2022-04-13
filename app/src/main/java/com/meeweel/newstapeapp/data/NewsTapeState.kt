package com.meeweel.newstapeapp.data

sealed class NewsTapeState {
    data class Success(val newsData: List<NewsPostDTO>) : NewsTapeState()
    class Error(val error: Throwable) : NewsTapeState()
    object Loading : NewsTapeState()
}
package com.example.news

import com.example.data.news.repository.News

sealed class NewsState {
    object Loading : NewsState()
    data class Error(val throwable: Throwable) : NewsState()
    data class Content(val newsList: List<News>) : NewsState()
}
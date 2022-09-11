package com.example.data.news.remote

import com.example.data.main.NetworkClient
import com.example.data.utils.runOperationCatching
import com.example.data.utils.Result
import kotlinx.coroutines.delay

class NewsRemoteDataSource {
    suspend fun getNews(): Result<NewsDto.Response, Throwable> {
        return runOperationCatching {
            delay(3000L)
            NetworkClient.create().getSampleData(NewsDto.Request(1))
        }
    }
}
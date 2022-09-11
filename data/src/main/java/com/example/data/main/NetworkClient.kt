package com.example.data.main

import com.example.data.news.remote.NewsApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    fun create(): NewsApiService =
        Retrofit.Builder().client(OkHttpClient.Builder().addInterceptor(MockInterceptor()).build())
            .addConverterFactory(GsonConverterFactory.create()).baseUrl("https://www.mts.ru/")
            .build().create(NewsApiService::class.java)
}
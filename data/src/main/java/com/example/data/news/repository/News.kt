package com.example.data.news.repository

import com.example.data.news.db.NewsEntity

data class News(
    val id: Int? = null,
    val title: String? = null,
    val text: String? = null,
    val author: String? = null
)

fun News.toData() = NewsEntity(
    this.id ?: 0,
    this.title ?: "",
    this.text ?: "",
    this.author ?: ""
)
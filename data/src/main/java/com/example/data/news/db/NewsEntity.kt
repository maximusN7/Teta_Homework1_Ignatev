package com.example.data.news.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.news.repository.News

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val text: String,
    val author: String
)

fun NewsEntity.toDomain() = News(this.id, this.title, this.text, this.author)
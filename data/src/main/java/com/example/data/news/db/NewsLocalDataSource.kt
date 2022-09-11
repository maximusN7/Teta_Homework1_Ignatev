package com.example.data.news.db

import android.content.Context
import com.example.data.main.AppDatabase
import com.example.data.news.repository.News
import com.example.data.news.repository.toData
import com.example.data.utils.runOperationCatching
import com.example.data.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class NewsLocalDataSource(private val context: Context) {
    suspend fun getNews(): Result<List<NewsEntity>, Throwable> {
        return runOperationCatching {
            delay(1000L)
            withContext(Dispatchers.IO) {
                AppDatabase.getDatabase(context).newsDao().getAll() ?: listOf(
                    NewsEntity(
                        0,
                        "nothing",
                        "nothing",
                        "no one"
                    ), NewsEntity(0, "nothing", "nothing", "no one")
                )
            }
        }
    }

    suspend fun insertNews(news: List<News>) {
        withContext(Dispatchers.IO) {
            val list: MutableList<NewsEntity> = mutableListOf()
            for (new in news) {
                list.add(new.toData())
            }
            clearDB()
            AppDatabase.getDatabase(context).newsDao().insertAll(list)
        }
    }

    fun clearDB() {
        AppDatabase.getDatabase(context).newsDao().deleteAll()
    }
}
package com.example.data.news.repository

import com.example.data.news.db.NewsLocalDataSource
import com.example.data.news.db.toDomain
import com.example.data.news.remote.NewsRemoteDataSource
import com.example.data.news.remote.toDomain
import com.example.data.utils.Result
import com.example.data.utils.doOnError
import com.example.data.utils.doOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val newsRemoteDataSource: NewsRemoteDataSource
) {
    suspend fun getNews(): Flow<Result<List<News>, Throwable>> {
        return flow {
            newsLocalDataSource.getNews()
                .doOnError {
                    emit(Result.Error(it))
                }
                .doOnSuccess {
                    val list: MutableList<News> = mutableListOf()
                    for (news in it) {
                        list.add(news.toDomain())
                    }
                    if (list.size != 0) {
                        emit(Result.Success(list))
                    } else {
                        newsRemoteDataSource.getNews()
                            .doOnError {
                                emit(Result.Error(it))
                            }
                            .doOnSuccess {
                                val list1: List<News> = it.toDomain()
                                newsLocalDataSource.insertNews(list1)
                                emit(Result.Success(list1))
                            }
                    }
                }
        }
    }

    fun clearDB() {
        newsLocalDataSource.clearDB()
    }
}
package com.example.data.news.db

import androidx.room.*

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): List<NewsEntity>?

    @Query("SELECT * FROM news WHERE id = :id")
    fun getById(id: Int): NewsEntity?

    @Insert
    fun insert(news: NewsEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsEntity>)

    @Update
    fun update(news: NewsEntity?)

    @Delete
    fun delete(news: NewsEntity?)

    @Query("DELETE FROM news")
    fun deleteAll()
}
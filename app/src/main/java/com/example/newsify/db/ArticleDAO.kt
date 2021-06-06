package com.example.newsify.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsify.model.Article

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun delete(article: Article)
}
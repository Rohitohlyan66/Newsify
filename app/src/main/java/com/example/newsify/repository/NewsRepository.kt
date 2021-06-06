package com.example.newsify.repository

import com.example.newsify.api.RetrofitInstance
import com.example.newsify.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun getSearchedNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchQuery, pageNumber)


}
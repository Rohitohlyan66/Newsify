package com.example.newsify.repository

import com.example.newsify.api.RetrofitInstance
import com.example.newsify.db.ArticleDatabase
import com.example.newsify.model.Article

class NewsRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun getSearchedNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDAO().upsert(article)

    suspend fun delete(article: Article) = db.getArticleDAO().delete(article)

    fun getSavedNews() = db.getArticleDAO().getAllArticles()


}
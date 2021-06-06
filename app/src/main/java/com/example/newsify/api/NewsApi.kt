package com.example.newsify.api

import com.example.newsify.model.NewsResponse
import com.example.newsify.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") country: String,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ) : Response<NewsResponse>



}
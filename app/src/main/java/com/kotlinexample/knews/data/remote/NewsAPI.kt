package com.kotlinexample.knews.data.remote

import com.kotlinexample.knews.data.remote.dto.NewsResponse
import com.kotlinexample.knews.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("page") page : Int,
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse


    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun getEverything(
        @Query("page") page : Int,
        @Query("sources") sources : String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}
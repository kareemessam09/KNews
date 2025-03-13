package com.kotlinexample.knews.data.remote.dto

import com.kotlinexample.knews.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
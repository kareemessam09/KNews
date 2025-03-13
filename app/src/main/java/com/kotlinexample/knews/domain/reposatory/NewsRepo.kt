package com.kotlinexample.knews.domain.reposatory

import androidx.paging.PagingData
import com.kotlinexample.knews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    fun getEveryThing(sources: List<String>): Flow<PagingData<Article>>

    fun getTopHeadlines(): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>


}
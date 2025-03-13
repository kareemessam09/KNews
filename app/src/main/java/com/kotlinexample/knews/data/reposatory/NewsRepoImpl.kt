package com.kotlinexample.knews.data.reposatory

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kotlinexample.knews.data.remote.NewsAPI
import com.kotlinexample.knews.data.remote.NewsPagingSource
import com.kotlinexample.knews.data.remote.NewsPagingSource2
import com.kotlinexample.knews.data.remote.SearchPagingSource
import com.kotlinexample.knews.domain.reposatory.NewsRepo
import com.kotlinexample.knews.domain.model.Article
import kotlinx.coroutines.flow.Flow
import java.util.Locale.Category

class NewsRepoImpl (private val newsApi: NewsAPI): NewsRepo {

    override fun getEveryThing(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                NewsPagingSource(newsApi = newsApi, sources = sources.joinToString(separator = ","))
            }
        ).flow
    }

    override fun getTopHeadlines(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = {
                NewsPagingSource2(newsApi = newsApi)
            }
        ).flow
    }



    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SearchPagingSource(newsApi = newsApi, searchQuery = searchQuery, sources = sources.joinToString(separator = ","))
            }
        ).flow
    }


}
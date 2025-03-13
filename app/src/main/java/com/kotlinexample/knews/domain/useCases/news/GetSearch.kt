package com.kotlinexample.knews.domain.useCases.news

import androidx.paging.PagingData
import com.kotlinexample.knews.domain.model.Article
import com.kotlinexample.knews.domain.reposatory.NewsRepo
import com.kotlinexample.knews.ui.screens.search.SearchEvent
import kotlinx.coroutines.flow.Flow

class GetSearch (
    private val newsRepo: NewsRepo
) {

    operator fun invoke(searchQuery: String,sources: List<String>): Flow<PagingData<Article>> {
        return newsRepo.searchNews(searchQuery,sources)
    }
}
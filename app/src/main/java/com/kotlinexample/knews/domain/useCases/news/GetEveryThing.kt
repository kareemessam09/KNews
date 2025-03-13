package com.kotlinexample.knews.domain.useCases.news

import androidx.paging.PagingData
import com.kotlinexample.knews.domain.model.Article
import com.kotlinexample.knews.domain.reposatory.NewsRepo
import kotlinx.coroutines.flow.Flow

class GetEveryThing(
    private val newsRepo: NewsRepo
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepo.getEveryThing(sources)
    }
}
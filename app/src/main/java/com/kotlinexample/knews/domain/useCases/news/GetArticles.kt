package com.kotlinexample.knews.domain.useCases.news

import com.kotlinexample.knews.data.local.NewsDao
import com.kotlinexample.knews.domain.model.Article
import kotlinx.coroutines.flow.Flow

class GetArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

}
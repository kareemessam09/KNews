package com.kotlinexample.knews.domain.useCases.news

import com.kotlinexample.knews.data.local.NewsDao
import com.kotlinexample.knews.domain.model.Article
import kotlinx.coroutines.flow.Flow

class GetArticle (
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(url: String): Article?{
        return newsDao.getArticle(url = url)
    }

}
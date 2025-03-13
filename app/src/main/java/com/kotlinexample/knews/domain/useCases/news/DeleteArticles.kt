package com.kotlinexample.knews.domain.useCases.news

import com.kotlinexample.knews.data.local.NewsDao
import com.kotlinexample.knews.domain.model.Article

class DeleteArticle (
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.delete(article = article)
    }

}
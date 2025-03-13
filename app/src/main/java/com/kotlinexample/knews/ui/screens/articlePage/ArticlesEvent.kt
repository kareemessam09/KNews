package com.kotlinexample.knews.ui.screens.articlePage

import com.kotlinexample.knews.domain.model.Article

sealed class ArticlesEvent {

    data class UpsertDeleteArticle(val article: Article) : ArticlesEvent()

    object RemoveSideEffect : ArticlesEvent()

}
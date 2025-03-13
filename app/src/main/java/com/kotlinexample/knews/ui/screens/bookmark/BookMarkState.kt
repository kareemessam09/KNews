package com.kotlinexample.knews.ui.screens.bookmark

import com.kotlinexample.knews.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
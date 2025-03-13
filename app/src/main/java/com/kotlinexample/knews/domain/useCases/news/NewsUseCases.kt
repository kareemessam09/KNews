package com.kotlinexample.knews.domain.useCases.news

data class NewsUseCases(
    val getEveryThing: GetEveryThing,
    val getTopHeadLines: GetTopHeadLines,
    val getSearch: GetSearch,
    val deleteArticle: DeleteArticle,
    val getArticles: GetArticles,
    val saveArticle: SaveArticle,
    val getArticle: GetArticle
)

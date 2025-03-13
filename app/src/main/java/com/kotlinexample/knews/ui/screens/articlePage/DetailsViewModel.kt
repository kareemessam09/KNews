package com.kotlinexample.knews.ui.screens.articlePage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinexample.knews.domain.model.Article
import com.kotlinexample.knews.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

import com.kotlinexample.knews.util.UIComponent

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: ArticlesEvent) {
        when (event) {
            is ArticlesEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.getArticle(url = event.article.url)
                    if (article == null){
                        upsertArticle(article = event.article)
                    }else{
                        deleteArticle(article = event.article)
                    }
                }
            }
            is ArticlesEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article = article)
        sideEffect = UIComponent.Toast("Article deleted")
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.saveArticle(article = article)
        sideEffect = UIComponent.Toast("Article Inserted")
    }

}
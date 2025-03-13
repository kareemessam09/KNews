package com.kotlinexample.knews.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kotlinexample.knews.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    val news = newsUseCases.getEveryThing(
       sources =  listOf("bbc-news" , "cnn" , "fox-news" ,"al-jazeera-english", "the-verge", "the-wall-street-journal" , "the-washington-post" , "time")
    ).cachedIn(viewModelScope)


    val topHeadLines = newsUseCases.getTopHeadLines().cachedIn(viewModelScope)






}
package com.kotlinexample.knews.di

import android.app.Application
import androidx.room.Room
import com.kotlinexample.knews.data.local.NewsDao
import com.kotlinexample.knews.data.local.NewsDatabase
import com.kotlinexample.knews.data.local.NewsTypeConvertor
import com.kotlinexample.knews.data.manager.LocalUsageManagerImpl
import com.kotlinexample.knews.data.remote.NewsAPI
import com.kotlinexample.knews.data.reposatory.NewsRepoImpl
import com.kotlinexample.knews.domain.manager.LocalUsageManager
import com.kotlinexample.knews.domain.reposatory.NewsRepo
import com.kotlinexample.knews.domain.useCases.app_entry.AppEntryUseCase
import com.kotlinexample.knews.domain.useCases.app_entry.DeleteAppEntry
import com.kotlinexample.knews.domain.useCases.app_entry.ReadAppEntry
import com.kotlinexample.knews.domain.useCases.app_entry.SaveAppEntry
import com.kotlinexample.knews.domain.useCases.news.DeleteArticle
import com.kotlinexample.knews.domain.useCases.news.GetArticle
import com.kotlinexample.knews.domain.useCases.news.GetArticles
import com.kotlinexample.knews.domain.useCases.news.GetCategory
import com.kotlinexample.knews.domain.useCases.news.GetEveryThing
import com.kotlinexample.knews.domain.useCases.news.GetSearch
import com.kotlinexample.knews.domain.useCases.news.GetTopHeadLines
import com.kotlinexample.knews.domain.useCases.news.NewsUseCases
import com.kotlinexample.knews.domain.useCases.news.SaveArticle
import com.kotlinexample.knews.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideLocalUsageManager(
        application: Application
    ): LocalUsageManager = LocalUsageManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCase(

        localUsageManager: LocalUsageManager

    ) = AppEntryUseCase(
        ReadAppEntry(localUsageManager),
        SaveAppEntry(localUsageManager),
        DeleteAppEntry(localUsageManager)
    )


    @Provides
    @Singleton
    fun provideApiInstance(): NewsAPI {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsAPI,
        newsDao: NewsDao
    ): NewsRepo {
        return NewsRepoImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCase(
        newsRepo: NewsRepo,
        newsDao: NewsDao
    ): NewsUseCases{
        return NewsUseCases(
            getEveryThing = GetEveryThing(newsRepo),
            getTopHeadLines = GetTopHeadLines(newsRepo),
            getSearch = GetSearch(newsRepo),
            deleteArticle = DeleteArticle(newsDao),
            getArticles = GetArticles(newsDao),
            saveArticle = SaveArticle(newsDao),
            getArticle = GetArticle(newsDao),

        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = "news_db"
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao







}
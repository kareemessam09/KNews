package com.kotlinexample.knews.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.kotlinexample.knews.domain.model.Article
import com.kotlinexample.knews.ui.navigation.news_navigator.NewsNavigator
import com.kotlinexample.knews.ui.screens.articlePage.ArticleScreen
import com.kotlinexample.knews.ui.screens.home.HomeScreen
import com.kotlinexample.knews.ui.screens.home.HomeViewModel
import com.kotlinexample.knews.ui.screens.onBoarding.OnBoardingScreen
import com.kotlinexample.knews.ui.screens.onBoarding.OnBoardingViewModel
import com.kotlinexample.knews.ui.screens.search.SearchPage
import com.kotlinexample.knews.ui.screens.search.SearchState
import com.kotlinexample.knews.ui.screens.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String
    ) {

    val viewModel: HomeViewModel = hiltViewModel()
    val articles = viewModel.news.collectAsLazyPagingItems()
    val topHeadLines = viewModel.topHeadLines.collectAsLazyPagingItems()

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.Onboarding.route
        ) {

            composable(
                Route.Onboarding.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = {
                        viewModel.onEvent(it)
                    }
                )
            }

        }


        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route){
                NewsNavigator()
            }
        }


    }
}





package com.kotlinexample.knews.ui.navigation.news_navigator

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.kotlinexample.knews.R
import com.kotlinexample.knews.domain.model.Article
import com.kotlinexample.knews.ui.navigation.Route
import com.kotlinexample.knews.ui.navigation.news_navigator.components.BottomNavigationItem
import com.kotlinexample.knews.ui.navigation.news_navigator.components.NewsBottomNavigation
import com.kotlinexample.knews.ui.screens.articlePage.ArticleScreen
import com.kotlinexample.knews.ui.screens.articlePage.DetailsViewModel
import com.kotlinexample.knews.ui.screens.bookmark.BookmarkScreen
import com.kotlinexample.knews.ui.screens.bookmark.BookmarkViewModel
import com.kotlinexample.knews.ui.screens.home.HomeScreen
import com.kotlinexample.knews.ui.screens.home.HomeViewModel
import com.kotlinexample.knews.ui.screens.search.SearchPage
import com.kotlinexample.knews.ui.screens.search.SearchViewModel
import com.kotlinexample.knews.ui.theme.mainBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.baseline_home_24, text = "Home"),
            BottomNavigationItem(icon = R.drawable.search_icon, text = "Search"),
            BottomNavigationItem(icon = R.drawable.bookmark_24, text = "Bookmark"),
        )
    }



    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.Home.route -> 0
        Route.Search.route -> 1
        Route.Bookmark.route -> 2
        else -> 0
    }


    Scaffold(modifier = Modifier.fillMaxSize().background(mainBlack), bottomBar = {
        NewsBottomNavigation(
            items = bottomNavigationItems,
            selectedItem = selectedItem,
            onItemClick = { index ->
                when (index) {
                    0 -> navigateToTab(
                        navController = navController,
                        route = Route.Home.route
                    )

                    1 -> navigateToTab(
                        navController = navController,
                        route = Route.Search.route
                    )

                    2 -> navigateToTab(
                        navController = navController,
                        route = Route.Bookmark.route
                    )
                }
            }
        )
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.Home.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.Home.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigate = { navigateToTab(navController = navController, route = it) },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article,
                        )
                    }
                )
            }
            composable(route = Route.Search.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchPage(searchState = state, event = viewModel::onEvent , navigateToDetails = { article ->
                    navigateToDetails(
                        navController = navController,
                        article = article
                    )
                }, navigate = { navigateToTab(navController = navController, route = it) })
            }

            composable(route = Route.NewsDetails.route) {
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        ArticleScreen(
                            article = article,
                            navigateUp = { navController.navigateUp() },
                        )
                    }

            }
            composable(route = Route.Bookmark.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.Home.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, article: Article){
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.NewsDetails.route
    )
}
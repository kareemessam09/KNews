package com.kotlinexample.knews.ui.navigation

sealed class Route (
    val route: String
){
    object Onboarding : Route("onboarding")
    object Home : Route("home")
    object Search : Route("search")
    object Bookmark : Route("bookmark")
    object NewsDetails : Route("newsDetails")
    object AppStartNavigation : Route("appStartNavigation")
    object NewsNavigation : Route("newsNavigation")
    object NewsNavigatorScreen : Route(route = "newsNavigator")

}
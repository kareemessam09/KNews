package com.kotlinexample.knews.ui.screens.onBoarding

sealed class OnboardingEvent {


    data object saveAppEntry : OnboardingEvent()

}
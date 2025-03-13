package com.kotlinexample.knews.ui.screens.onBoarding.components

import androidx.annotation.DrawableRes
import com.kotlinexample.knews.R

data class Page (
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf<Page>(
    Page(
        "Stay Informed, Anytime, Anywhere",
        "Get the latest news from trusted sources, personalized to your interests.",
        R.drawable.onboard1

    ),
    Page(
        "Be the First to Know, Always",
        "Get instant notifications on major news stories, so you’re always in the loop.",
        R.drawable.onboard3

    ),Page(
        "Read Anytime, Even Without Internet",
        "Save articles and read them later, even when you’re offline. Never miss a story!",
        R.drawable.onboard2

    ),Page(
        "Read Comfortably, Day or Night",
        "Switch to dark mode for a better reading experience that’s easy on your eyes.",
        R.drawable.onboard4
    ),

)
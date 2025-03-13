package com.kotlinexample.knews.ui.screens.onBoarding

import android.view.Display.Mode
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kotlinexample.knews.ui.screens.onBoarding.components.NewsButton
import com.kotlinexample.knews.ui.screens.onBoarding.components.NewsTextButton
import com.kotlinexample.knews.ui.screens.onBoarding.components.OnBoardingPage
import com.kotlinexample.knews.ui.screens.onBoarding.components.PageIndicator
import com.kotlinexample.knews.ui.screens.onBoarding.components.pages
import com.kotlinexample.knews.ui.theme.mainBlack
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event : (OnboardingEvent) -> Unit
) {

    Column (modifier = Modifier.fillMaxSize().background(mainBlack)) {

        val pagerState = rememberPagerState(initialPage = 0){
            pages.size
        }


        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    pages.size - 1 -> listOf("Back", "Get Started")
                    else -> listOf("Back", "Next")
                }
            }

        }

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(
                page = pages[index]
            )

        }


        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            PageIndicator(
                modifier = Modifier,
                pageSize = pages.size,
                selectedPage = pagerState.currentPage,
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage - 1
                                )
                            }

                        }
                    )
                }
                NewsButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 3){
                                event(OnboardingEvent.saveAppEntry)
                            }else{
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))



    }


}
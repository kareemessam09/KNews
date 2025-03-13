package com.kotlinexample.knews.ui.screens.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kotlinexample.knews.R
import com.kotlinexample.knews.domain.model.Article
import com.kotlinexample.knews.ui.screens.home.TrendedList
import com.kotlinexample.knews.ui.screens.home.components.TrendingNowItem
import com.kotlinexample.knews.ui.theme.mainBlack
import com.kotlinexample.knews.ui.theme.mainRed

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {


        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier
                .background(mainBlack)
                .fillMaxSize()
                .statusBarsPadding(),
            contentPadding = PaddingValues(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(state.articles) { article ->
                TrendingNowItem(
                    article = article,
                    onClick = { navigateToDetails(article) }
                )
            }
        }





}
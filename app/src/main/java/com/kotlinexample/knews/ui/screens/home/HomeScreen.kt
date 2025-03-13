package com.kotlinexample.knews.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.navOptions
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kotlinexample.knews.R
import com.kotlinexample.knews.domain.model.Article
import com.kotlinexample.knews.ui.screens.common.ArticleCardShimmerEffect
import com.kotlinexample.knews.ui.screens.common.EmptyScreen
import com.kotlinexample.knews.ui.screens.home.components.CategoryListItem
import com.kotlinexample.knews.ui.screens.home.components.MainListItem
import com.kotlinexample.knews.ui.screens.home.components.TrendingNowItem
import com.kotlinexample.knews.ui.screens.search.SearchPage
import com.kotlinexample.knews.ui.screens.search.SearchState
import com.kotlinexample.knews.ui.screens.search.SearchViewModel
import com.kotlinexample.knews.ui.theme.KNewsTheme
import com.kotlinexample.knews.ui.theme.mainBlack
import com.kotlinexample.knews.ui.theme.mainRed
import com.kotlinexample.knews.ui.theme.mainWhite
import com.kotlinexample.knews.ui.theme.secondBlack


@Composable
fun HomeScreen(
               articles: LazyPagingItems<Article>,
               navigateToDetails: (Article) -> Unit,
               navigate:(String) -> Unit,
) {




    Column (
        modifier = Modifier
            .background(mainBlack)
            .fillMaxSize()
            .statusBarsPadding()
    ){
        TopBar()
        MainList(navigateToDetails)
        TrendedList(articles = articles, onClick = navigateToDetails)
    }




}


@Composable
fun TopBar(

){

    Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background( mainBlack)
                .fillMaxWidth()
                .height(64.dp)
                .padding(top = 12.dp),

            ) {

            Image(
                modifier = Modifier
                    .background(mainBlack)
                    .padding(start = 8.dp)
                    .size(width = 90.dp, height = 64.dp),
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(width = 40.dp, height = 35.dp)
                    .clickable {

                    },

                painter = painterResource(id = R.drawable.dark_mode),
                contentDescription = "Dark mode"
            )


        }




}


@Composable
fun MainList(
    navigateToDetails: (Article) -> Unit
){
    val viewModel: HomeViewModel = hiltViewModel()
    val topOnes = viewModel.topHeadLines.collectAsLazyPagingItems()

    val handlePagingResult = handlePagingResult(topOnes)


    if (handlePagingResult) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            items(
                count = topOnes.itemCount,
            ) {
                topOnes[it]?.let { article ->
                    MainListItem(article, navigateToDetails = navigateToDetails)
                }
            }
        }
    }

}



@Composable
fun TrendedList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick:(Article) -> Unit,

) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background( mainBlack)
    ) {

        Row(
            modifier = Modifier.padding(bottom = 12.dp)
        ) {

            Text(
                text = "TRENDING NOW",
                color = mainWhite,
                modifier = Modifier
                    .padding(start = 12.dp, top = 18.dp)
                    .weight(1f)
            )


        }


        val handlePagingResult = handlePagingResult(articles)



            if (handlePagingResult) {
                LazyColumn(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    contentPadding = PaddingValues(all = 8.dp)
                ) {
                    items(
                        count = articles.itemCount,
                    ) {
                        articles[it]?.let { article ->
                            TrendingNowItem(article = article, onClick = {onClick(article)})
                        }
                    }
                }
            }




    }
}




@Composable
fun handlePagingResult(articles: LazyPagingItems<Article>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)){
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}





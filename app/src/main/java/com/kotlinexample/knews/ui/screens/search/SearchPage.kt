package com.kotlinexample.knews.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.kotlinexample.knews.domain.model.Article
import com.kotlinexample.knews.ui.screens.home.components.TrendingNowItem
import com.kotlinexample.knews.ui.theme.mainBlack
import com.kotlinexample.knews.ui.theme.mainRed
import com.kotlinexample.knews.ui.theme.mainWhite


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(
    searchState: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit,
    navigate:(String) -> Unit
) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(mainBlack)
            .statusBarsPadding()

    ){

        OutlinedTextField(
            value = searchState.searchQuery,
            onValueChange = {
                event(SearchEvent.UpdateSearchQuery(it))
                if (searchState.searchQuery != " ") {
                    event(SearchEvent.SearchNews)
                }
            },
            label = { Text("Search" , color = mainWhite.copy(alpha = 0.6f)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon" , tint = mainWhite.copy(alpha = 0.6f)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = mainWhite, // Border color when the field is focused
                unfocusedBorderColor = mainRed // Border color when the field is not focused
            ),
            textStyle = TextStyle( // Customize text style
                color = mainWhite,
                fontSize = 18.sp
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search // Show "Search" action on the keyboard
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    event(SearchEvent.SearchNews) // Call the search event when Enter is pressed
                }
            )
        )

        val articles = searchState.articles?.collectAsLazyPagingItems()

        articles?.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)
            ) {
                items(it.itemCount) { index ->
                    it[index]?.let { article ->
                        TrendingNowItem(article = article, onClick = { navigateToDetails(article) })
                    }
                }
            }
        }
        



    }



}

@Preview(showSystemUi = true)
@Composable
fun SearchPagePreview() {
    //SearchPage()
}
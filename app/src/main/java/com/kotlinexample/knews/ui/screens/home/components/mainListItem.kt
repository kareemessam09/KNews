package com.kotlinexample.knews.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kotlinexample.knews.R
import com.kotlinexample.knews.domain.model.Article
import com.kotlinexample.knews.domain.model.Source
import com.kotlinexample.knews.ui.screens.common.getTimeDifference
import com.kotlinexample.knews.ui.theme.KNewsTheme
import com.kotlinexample.knews.ui.theme.mainBlack
import com.kotlinexample.knews.ui.theme.mainRed
import com.kotlinexample.knews.ui.theme.mainWhite


@Composable
fun MainListItem(
    article: Article,
    navigateToDetails: (Article) -> Unit
) {

    val context = LocalContext.current


    Column (modifier = Modifier
        .height(250.dp)
        .width(300.dp)
        .background(color = mainBlack)
        .padding(10.dp)
        .clickable { navigateToDetails(article) }


    ){

        AsyncImage(
            modifier = Modifier
                .height(147.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(R.drawable.baseline_broken_image_24),
            error = painterResource(R.drawable.baseline_broken_image_24)
        )


        Text(text = article.title, style = MaterialTheme.typography.titleLarge, color = mainWhite, modifier = Modifier.padding(top = 8.dp) , maxLines = 2 , overflow = TextOverflow.Ellipsis)


        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ){
            Text(text = article.source.name, style = MaterialTheme.typography.labelSmall, color = Color.Gray)

            Spacer(modifier = Modifier.width(10.dp))


            Icon(
                painter = painterResource(id = R.drawable.time_24),
                contentDescription = null,
                modifier = Modifier.size(15.dp),
                tint = mainRed

            )

            Text(text = getTimeDifference(article.publishedAt), style = MaterialTheme.typography.labelSmall, color = Color.Gray , modifier = Modifier.padding(start = 4.dp))

        }

    }
}



@Preview(showBackground = true)
@Composable
fun MainListItemPreview() {
    KNewsTheme {
        MainListItem(article = Article(
            author = "",
            content = "",
            description = "",
            publishedAt = "2 hours",
            source = Source(id = "", name = "BBC"),
            title = "Her train broke down. Her phone died. And then she met her Saver in a",
            url = "",
            urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg"
        ),

            navigateToDetails = { }
        )
    }
}
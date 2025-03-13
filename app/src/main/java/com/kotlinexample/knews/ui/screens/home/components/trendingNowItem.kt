package com.kotlinexample.knews.ui.screens.home.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.compose.AsyncImage
import com.kotlinexample.knews.R
import com.kotlinexample.knews.domain.model.Article
import com.kotlinexample.knews.domain.model.Source
import com.kotlinexample.knews.ui.screens.common.getTimeDifference
import com.kotlinexample.knews.ui.theme.KNewsTheme
import com.kotlinexample.knews.ui.theme.mainBlack
import com.kotlinexample.knews.ui.theme.mainRed
import com.kotlinexample.knews.ui.theme.mainWhite
import com.kotlinexample.knews.ui.theme.secondBlack
import java.time.Duration
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@Composable
fun TrendingNowItem(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
){

    val context = LocalContext.current

    Row (
        modifier = Modifier
            .padding(start = 8.dp, top = 10.dp, end = 8.dp)
            .height(65.dp)
            .fillMaxWidth()
            .clickable { onClick.invoke() }
    ){

        AsyncImage(
            modifier = Modifier
                .height(65.dp)
                .width(65.dp)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.baseline_broken_image_24),
            error = painterResource(R.drawable.baseline_broken_image_24)
        )


        Column {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                color = mainWhite,
                modifier = Modifier
                    .padding(start = 8.dp) ,
                maxLines = 2
            )

            Row {

                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color.Gray ,
                    modifier = Modifier
                                .padding(start = 8.dp) ,
                )

                Spacer(modifier = Modifier.width(10.dp))

                Icon(
                    painter = painterResource(id = R.drawable.time_24),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp),
                    tint = mainRed

                )
                Text(
                    text = getTimeDifference(article.publishedAt),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = 2.dp)
                )
            }
        }





    }


}



@Preview
@Composable
fun TrendingNowItemPreview(){
    KNewsTheme {
        TrendingNowItem(
            article = Article(
                author = "",
                content = "",
                description = "",
                publishedAt = "2 hours",
                source = Source(id = "", name = "BBC"),
                title = "Her train broke down. Her phone died. And then she met her Saver in a",
                url = "",
                urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg"
            ),
            onClick = {}
        )
    }
}
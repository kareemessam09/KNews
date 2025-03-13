package com.kotlinexample.knews.ui.screens.articlePage

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kotlinexample.knews.R
import com.kotlinexample.knews.domain.model.Article
import com.kotlinexample.knews.domain.model.Source
import com.kotlinexample.knews.ui.navigation.Route
import com.kotlinexample.knews.ui.screens.articlePage.components.ArticleContent
import com.kotlinexample.knews.ui.screens.common.getTimeDifference
import com.kotlinexample.knews.ui.theme.KNewsTheme
import com.kotlinexample.knews.ui.theme.mainBlack
import com.kotlinexample.knews.ui.theme.mainRed
import com.kotlinexample.knews.ui.theme.mainWhite
import com.kotlinexample.knews.util.UIComponent


@Composable
fun ArticleScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    article: Article,
) {

    val context = LocalContext.current

    val viewModel: DetailsViewModel = hiltViewModel()
    val event = viewModel::onEvent
    val sideEffect = viewModel.sideEffect



    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when(sideEffect){
                is UIComponent.Toast ->{
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    event(ArticlesEvent.RemoveSideEffect)
                }
                else -> Unit
            }
        }
    }


    Column (
        modifier
            .background(mainBlack)
            .fillMaxSize()
            .padding(horizontal = 15.dp)
            .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
        ,
    ){


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(mainBlack)
                .height(65.dp)
                .padding(bottom = 15.dp, top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){


                Image(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            navigateUp()
                             },

                    painter = painterResource (id = R.drawable.back24),
                    contentDescription = "Back",
                )

            Spacer(modifier = Modifier.weight(1f)) // Pushes the next items to the right


            Image(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            event(ArticlesEvent.UpsertDeleteArticle(article))
                        },
                    painter = painterResource(id = R.drawable.bookmark_border_24),
                    contentDescription = "Back",

                )

                Image(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(35.dp)
                        .clickable {
                            Intent(Intent.ACTION_VIEW).also {
                                it.data = Uri.parse(article.url)
                                if (it.resolveActivity(context.packageManager) != null) {
                                    context.startActivity(it)
                                }
                        }
                    },
                    painter = painterResource(id = R.drawable.public_24),
                    contentDescription = "Back",

                )

        }



        Text(
            text = article.title,
            modifier = Modifier,
            color = mainWhite,
            style =  MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                fontSize = 16.sp
            ),
            maxLines = 3,
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
        ){
            Text(
                text =  article.source.name,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.Gray ,
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


        AsyncImage(
            model = ImageRequest.Builder(context = context).data(article.urlToImage)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .padding(top = 10.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )



        Text(
            text = duplicateIfLessThan7Lines(article.content).ifEmpty { "Content" },
            modifier = Modifier.padding(top = 10.dp),
            color = mainWhite,
            lineHeight = 21.sp,
            style =  MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),

            )


    }






}


fun duplicateIfLessThan7Lines(text: String): String {
    val lines = text.lines()
    return if (lines.size < 7) text + "\n" + text + "\n"  + "\n"+ text + "\n" + text else text
}


@Preview(showSystemUi = true)
@Composable
fun ArticleScreenPreview() {
    KNewsTheme {
        ArticleScreen(
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
            navigateUp = {}
        )
    }
}
package com.kotlinexample.knews.ui.screens.onBoarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlinexample.knews.R
import com.kotlinexample.knews.ui.theme.Grey
import com.kotlinexample.knews.ui.theme.KNewsTheme
import com.kotlinexample.knews.ui.theme.mainBlack
import com.kotlinexample.knews.ui.theme.mainRed
import com.kotlinexample.knews.ui.theme.mainWhite

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page : Page
) {

    Column (modifier = modifier) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f)
            ,
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = mainRed,
            text = page.title
        )
        Spacer(modifier = Modifier.height(10.dp))


        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold , fontSize = 20.sp),
            color = Grey,
            text = page.description
        )

    }





}

@Preview(showSystemUi = true)
@Composable
fun PreviewOnBoard(){

    KNewsTheme {
        OnBoardingPage(
            page = Page(
                "Stay Informed, Anytime, Anywhere",
                "Get the latest news from trusted sources, personalized to your interests. Stay ahead with real-time updates",
                R.drawable.onboard1
            )
        )
    }

}
package com.kotlinexample.knews.ui.screens.articlePage.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlinexample.knews.R
import com.kotlinexample.knews.ui.theme.KNewsTheme
import com.kotlinexample.knews.ui.theme.mainBlack
import com.kotlinexample.knews.ui.theme.mainWhite


@Composable
fun ArticleContent(
    modifier: Modifier = Modifier
) {


    Column (
        modifier
            .background(mainBlack)
            .fillMaxSize()
            .padding(horizontal = 15.dp)
            ,
        horizontalAlignment = Alignment.CenterHorizontally


    ){

        Text(
            text = "Diabetes Mellitus in Young Age: Causes and Characteristics",
            modifier = Modifier,
            color = mainWhite,
            style =  MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                fontSize = 16.sp
            ),
            maxLines = 3,
        )


        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .clip(MaterialTheme.shapes.medium)
                .padding(top = 10.dp)
            ,
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.download),
            contentDescription = "Article Image"

        )



        Text(
            text = "Lorem ipsum dolor sit amet consectetur. Interdum viverra vitae lectus mi quis pharetra. Vel fusce sed viverra cras a ante mauris libero adipiscing. Pellentesque urna nulla dictum lacus pharetra viverra urna nisl nisl. Bibendum fames nibh pellentesque at mus nunc risus. Sem cras eget eleifend cursus pretium id in vulputate. Dignissim vel vestibulum orci curabitur. Nullam fermentum sed nunc massa. Porttitor habitant facilisis vel arcu. Libero facilisis nisl consectetur nisi nunc a consequat. Ullamcorper augue massa nunc sagittis ipsum sed eu quisque. Morbi dui et neque urna consectetur nunc massa. Pharetra volutpat semper nisi faucibus. Sed lectus at libero hendrerit tristique nunc.",
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

@Preview(showSystemUi = true)
@Composable
fun ArticleContentPreview() {
    KNewsTheme {
        ArticleContent()
    }
}

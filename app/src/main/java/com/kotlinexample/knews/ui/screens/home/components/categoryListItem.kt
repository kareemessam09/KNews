package com.kotlinexample.knews.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlinexample.knews.R
import com.kotlinexample.knews.ui.theme.KNewsTheme
import com.kotlinexample.knews.ui.theme.mainBlack
import com.kotlinexample.knews.ui.theme.mainWhite
import com.kotlinexample.knews.ui.theme.secondBlack

@Composable
fun CategoryListItem() {

    Box(
        modifier = Modifier
            .padding(start = 8.dp , top = 18.dp , end = 8.dp , bottom = 18.dp)
            .height(160.dp)
            .width(140.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.diet),
            contentDescription = "Category Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(secondBlack),
            contentScale = ContentScale.FillBounds,
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(45.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, secondBlack.copy(alpha = 0.5f)), // Fades from transparent to black
                        startY = 10f,
                        endY = 80f,
                        tileMode = TileMode.Clamp
                    )
                )
        )

        Text(
            text = "Diabetic Lifestyle",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge.copy(
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.8f), // Soft shadow
                    blurRadius = 6f,
                    offset = Offset(2f, 2f)
                )
            ),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 15.dp, bottom = 15.dp),
            maxLines = 2,
        )
    }


}


@Preview(showBackground = true)
@Composable
fun CategoryListItemPreview() {
    KNewsTheme {
        CategoryListItem()
    }
}



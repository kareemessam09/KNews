package com.kotlinexample.knews.ui.screens.onBoarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlinexample.knews.ui.theme.mainRed

@Composable
fun PageIndicator(
    modifier: Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color =  mainRed,
    unselectedColor: Color = Color.Gray
) {

    Row (modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {

        repeat(pageSize) { page ->
            Box(modifier = modifier
                .size(14.dp)
                .clip(CircleShape)
                .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PageIndicatorPreview() {
    PageIndicator(modifier = Modifier, pageSize = 3, selectedPage = 0)
}
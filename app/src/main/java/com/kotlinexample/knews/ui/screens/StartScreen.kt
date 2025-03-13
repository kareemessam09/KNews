package com.kotlinexample.knews.ui.screens

import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlinexample.knews.R
import com.kotlinexample.knews.ui.theme.KNewsTheme
import com.kotlinexample.knews.ui.theme.mainBlack
import com.kotlinexample.knews.ui.theme.mainRed
import com.kotlinexample.knews.ui.theme.mainWhite

@Composable
fun StartScreen() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = mainBlack)
    ) {

        Image(
            modifier = Modifier
                .padding(top = 300.dp)
                .size(width = 300.dp, height = 92.dp)
                .align(Alignment.TopCenter)
                ,
            painter = painterResource(id = R.drawable.logoandslogan),
            contentDescription = "Logo and description"
        )

//        Button(
//            onClick = { /*TODO*/ },
//            shape = RoundedCornerShape(28.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = mainRed
//            ),
//            modifier = Modifier
//                .padding(bottom = 150.dp)
//                .size(width = 207.dp, height = 50.dp)
//                .align(Alignment.BottomCenter)
//
//
//
//                ){
//
//            Text(text = "START", color = mainWhite , style = MaterialTheme.typography.titleLarge  )
//
//        }


    }


}


@Preview(showBackground = true )
@Composable
fun StartScreenPreview() {
    KNewsTheme {
        StartScreen()
    }
}
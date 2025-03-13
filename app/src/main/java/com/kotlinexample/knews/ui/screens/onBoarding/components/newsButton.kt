package com.kotlinexample.knews.ui.screens.onBoarding.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlinexample.knews.ui.theme.Grey
import com.kotlinexample.knews.ui.theme.mainBlack
import com.kotlinexample.knews.ui.theme.mainRed

@Composable
fun NewsButton(
    text : String,
    onClick : () -> Unit
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = mainBlack,
            contentColor = mainRed
        ),
        shape = RoundedCornerShape(size = 6.dp)

        ) {


        Text(text = text , style = MaterialTheme.typography.titleLarge)

    }


}


@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            color = Grey
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewsButtonPreview() {
    NewsButton(text = "Start", onClick = {})
    NewsTextButton(text = "Sign in", onClick = {})
    }

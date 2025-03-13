package com.kotlinexample.knews.ui.navigation.news_navigator.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.drawable.Icon
import android.provider.CalendarContract
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlinexample.knews.R
import com.kotlinexample.knews.ui.theme.Grey
import com.kotlinexample.knews.ui.theme.KNewsTheme
import com.kotlinexample.knews.ui.theme.mainBlack
import com.kotlinexample.knews.ui.theme.mainRed
import com.kotlinexample.knews.ui.theme.mainWhite

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        containerColor = mainBlack,
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 10.dp,

    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor =mainRed,
                    selectedTextColor = mainWhite,
                    unselectedIconColor = mainWhite,
                    unselectedTextColor = mainWhite,
                    indicatorColor = mainWhite
                ),
            )
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)


@Preview(uiMode = UI_MODE_NIGHT_YES , showSystemUi = true)
@Composable
fun NewsBottomNavigationPreview() {
    KNewsTheme(dynamicColor = false) {
        NewsBottomNavigation(items = listOf(
            BottomNavigationItem(icon = R.drawable.dark_mode, text = "Home"),
            BottomNavigationItem(icon = R.drawable.search_icon, text = "Search"),
            BottomNavigationItem(icon = R.drawable.bookmark_24, text = "Bookmark"),
        ), selectedItem = 0, onItemClick = {})
    }
}
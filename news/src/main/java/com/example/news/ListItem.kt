package com.example.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.news.repository.News
import com.example.data.utils.ResourceProvider

@Composable
fun ListItem(resources: ResourceProvider, news: News) {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = news.title ?: "",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = news.text ?: "",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "${resources.getString("author")} ${news.author}",
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }
}
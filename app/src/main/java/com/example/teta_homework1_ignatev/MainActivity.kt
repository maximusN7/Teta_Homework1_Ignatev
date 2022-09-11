package com.example.teta_homework1_ignatev

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.data.news.db.NewsLocalDataSource
import com.example.data.news.remote.NewsRemoteDataSource
import com.example.data.news.repository.NewsRepository
import com.example.data.utils.ResourceProvider
import com.example.news.NewsScreen
import com.example.news.NewsViewModel
import com.example.teta_homework1_ignatev.ui.theme.TetaHomework1IgnatevTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TetaHomework1IgnatevTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NewsScreen(
                        NewsViewModel(
                            NewsRepository(
                                NewsLocalDataSource(applicationContext),
                                NewsRemoteDataSource(),
                            )
                        ),
                        ResourceProvider(this)
                    )
                }
            }
        }
    }
}
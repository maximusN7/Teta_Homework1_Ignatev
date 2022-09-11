package com.example.news

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.data.utils.ResourceProvider
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun NewsScreen(viewModel: NewsViewModel, resources: ResourceProvider) {
    val state = viewModel.state.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = {
            viewModel.refresh()
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (state.value) {
                is NewsState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                    )
                }
                is NewsState.Error -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if ((state.value as NewsState.Error).throwable.toString() != "") resources.getString(
                                "error_message"
                            ) else "",
                            modifier = Modifier.wrapContentSize(),
                            textAlign = TextAlign.Center
                        )
                        Button(
                            onClick = {
                                viewModel.getData()
                            },
                            contentPadding = PaddingValues(all = 12.dp)
                        ) {
                            Text(resources.getString("error_button"))
                        }
                    }
                }
                is NewsState.Content -> {
                    LazyColumn(
                        contentPadding = PaddingValues(all = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        if (state.value !is NewsState.Error) {
                            val list = (state.value as NewsState.Content).newsList
                            items(items = list) { news ->
                                ListItem(resources = resources, news = news)
                            }
                        }
                    }
                }
            }
        }
    }
}
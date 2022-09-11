package com.example.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.news.repository.NewsRepository
import com.example.data.utils.doOnError
import com.example.data.utils.doOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _state: MutableStateFlow<NewsState> = MutableStateFlow(NewsState.Loading)
    val state = _state.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _state.emit(NewsState.Loading)
            repository.getNews().collect {
                it.doOnError { error ->
                    _state.emit(NewsState.Error(error))
                }.doOnSuccess { news ->
                    _state.emit(NewsState.Content(news))
                }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.clearDB()
            }
            _isRefreshing.emit(true)
            repository.getNews().collect {
                it.doOnError { error ->
                    _state.emit(NewsState.Error(error))
                }.doOnSuccess { news ->
                    _state.emit(NewsState.Content(news))
                }
            }
            _isRefreshing.emit(false)
        }
    }
}
package com.example.carousellnews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carousellnews.network.model.NewsItem
import com.example.carousellnews.network.news.NewsSource
import com.example.carousellnews.utils.ioReturnTask
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope as scope
import java.util.*
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsSource: NewsSource) : ViewModel() {

    val newsLiveData: LiveData<ArrayList<NewsItem>>
        get() = _newsListLiveData
    private val _newsListLiveData = MutableLiveData<ArrayList<NewsItem>>()

    init {
        loadNews()
    }

    private fun loadNews() {
        scope.launch {
            val news = ioReturnTask {
                newsSource.getNewsList()
            }
            _newsListLiveData.postValue(news)
        }
    }

}
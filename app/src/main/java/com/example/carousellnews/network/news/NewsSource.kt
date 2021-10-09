package com.example.carousellnews.network.news

import androidx.lifecycle.MutableLiveData
import com.example.carousellnews.network.data.NewsApi
import com.example.carousellnews.network.model.NewsItem
import java.util.*
import javax.inject.Inject

class NewsSource @Inject constructor(private val api: NewsApi) {

    suspend fun getNewsList(): List<NewsItem> {
        return api.getNewsList()
    }

}
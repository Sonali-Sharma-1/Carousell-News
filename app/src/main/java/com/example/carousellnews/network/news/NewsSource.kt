package com.example.carousellnews.network.news

import com.example.carousellnews.network.data.NewsApi
import com.example.carousellnews.network.model.NewsItem
import javax.inject.Inject

class NewsSource @Inject constructor(private val api: NewsApi) {

    suspend fun getNewsList(): ArrayList<NewsItem> {
        return api.getNewsList()
    }

}
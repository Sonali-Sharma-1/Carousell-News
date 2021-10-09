package com.example.carousellnews.network.data

import com.example.carousellnews.network.model.NewsItem
import retrofit2.http.GET

interface NewsApi {
    @GET("android/carousell_news.json")
    suspend fun getNewsList(): ArrayList<NewsItem>
}
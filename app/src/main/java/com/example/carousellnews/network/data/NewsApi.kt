package com.example.carousellnews.network.data

import androidx.lifecycle.MutableLiveData
import com.example.carousellnews.network.model.NewsItem
import retrofit2.http.GET
import java.util.*

interface NewsApi {
    @GET("android/carousell_news.json")
    suspend fun getNewsList(): List<NewsItem>
}
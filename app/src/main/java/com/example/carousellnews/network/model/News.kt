package com.example.carousellnews.network.model

import java.util.*

data class News(val newsList: ArrayList<NewsItem>)

data class NewsItem(
    val banner_url: String,
    val description: String,
    val id: String,
    val rank: Int,
    val time_created: Int,
    val title: String
)
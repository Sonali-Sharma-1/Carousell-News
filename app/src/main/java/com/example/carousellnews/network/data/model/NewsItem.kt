package com.example.carousellnews.data.api.model

data class NewsItem(
    val banner_url: String,
    val description: String,
    val id: String,
    val rank: Int,
    val time_created: Int,
    val title: String
)
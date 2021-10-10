package com.example.carousellnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carousellnews.databinding.NewsListItemBinding
import com.example.carousellnews.network.model.NewsItem
import com.example.carousellnews.utils.DateFormat
import java.util.*
import javax.inject.Inject

class NewsAdapter @Inject constructor() : RecyclerView.Adapter<NewsAdapter.NewsDataViewHolder>() {

    private var news = mutableListOf<NewsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsDataViewHolder {
        return NewsDataViewHolder(
            NewsListItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: NewsDataViewHolder, position: Int) {
        val item = news[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = news.size


    fun addAllItems(newsList: List<NewsItem>) {
        this.news.clear()
        this.news.addAll(newsList)
        notifyDataSetChanged()
    }

    class NewsDataViewHolder(private val binding: NewsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: NewsItem) {
            binding.newsData = news
            println("debug:" + DateFormat.getTimeAgo(news.time_created))

        }
    }
}
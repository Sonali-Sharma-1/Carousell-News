package com.example.carousellnews.ui.news

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.carousellnews.network.model.NewsItem
import com.example.carousellnews.network.news.NewsSource
import com.example.carousellnews.utils.ioReturnTask
import kotlinx.coroutines.*
import androidx.lifecycle.viewModelScope as scope
import java.util.*
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsSource: NewsSource) : ViewModel() {

    val newsLiveData: LiveData<List<NewsItem>>
        get() = _newsListLiveData
    private var _newsListLiveData = MutableLiveData<List<NewsItem>>()

    init {
        loadNews()
    }

    private fun loadNews() {
        scope.launch {
            val news = ioReturnTask {
                println("debug: launching bg thread: ${Thread.currentThread().name}")
                newsSource.getNewsList()
            }

            _newsListLiveData.postValue(news)
        }
    }

    fun loadNewsOnBgThread() {
        loadNews()
    }


    companion object {
        @JvmStatic
        @BindingAdapter("newsImage")
        fun loadImage(view: ImageView, imageType: String) {
            Glide.with(view.context)
                .load(imageType)
                .into(view)
        }
    }

}
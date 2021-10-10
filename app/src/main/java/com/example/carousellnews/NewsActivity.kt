package com.example.carousellnews

import android.annotation.SuppressLint
import android.app.LauncherActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carousellnews.dagger.App
import com.example.carousellnews.dagger.module.viewModule.ViewModelFactory
import com.example.carousellnews.databinding.ActivityNewsBinding
import com.example.carousellnews.network.data.NewsApi
import com.example.carousellnews.network.model.News
import com.example.carousellnews.network.model.NewsItem
import com.example.carousellnews.network.news.NewsSource
import com.example.carousellnews.ui.news.NewsViewModel
import com.example.carousellnews.utils.DateFormat
import com.example.carousellnews.utils.ioReturnTask
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class NewsActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var newsAdapter: NewsAdapter

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        newsViewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
        updateUI()
    }

    private fun setUI() {
        binding.rvNews.itemAnimator = DefaultItemAnimator()
        binding.rvNews.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.rvNews.adapter = newsAdapter
        binding.rvNews.layoutManager = LinearLayoutManager(this)

    }

    private fun updateUI() {
        newsViewModel.newsLiveData.observe(this, Observer {
            var mutableList = mutableListOf<NewsItem>()
            mutableList = it as MutableList<NewsItem>
            calculateLatestTimeStamp(mutableList)

            newsAdapter.addAllItems(mutableList)
            println("debug: updating UI")
        })
    }

    @SuppressLint("NewApi")
    private fun calculateLatestTimeStamp(newsItem: MutableList<NewsItem>) {
        val listOfDates: ArrayList<String> = ArrayList<String>()
        newsItem.forEach {
            val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.ENGLISH)
            val date = simpleDateFormat.format(it.time_created * 1000L)
            listOfDates.add(date)
        }

        val dateTimeFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss")

        val result = listOfDates.sortByDescending {
            LocalDate.parse(it, dateTimeFormatter)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_recent) {
            CoroutineScope(IO).launch {
                sortNewsByDate()
            }

            return true
        }

        if (id == R.id.action_popular) {
            sortNewsByRank()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sortNewsByDate() {
        newsViewModel.loadNewsOnBgThread()
    }

    private fun sortNewsByRank() {}
}
package com.example.carousellnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carousellnews.dagger.App
import com.example.carousellnews.dagger.module.viewModule.ViewModelFactory
import com.example.carousellnews.databinding.ActivityNewsBinding
import com.example.carousellnews.ui.news.NewsViewModel
import java.util.*
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

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
            newsAdapter.addAllItems(it)
        })
    }
}
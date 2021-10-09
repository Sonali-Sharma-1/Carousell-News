package com.example.carousellnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carousellnews.dagger.App
import com.example.carousellnews.dagger.module.viewModule.ViewModelFactory
import com.example.carousellnews.databinding.ActivityMainBinding
import com.example.carousellnews.ui.news.NewsViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        newsObservor()
    }

    private fun newsObservor() {
        viewModel.newsLiveData.observe(this, Observer {
            binding.txtView.text = it.toString()
            Log.d("info", it.toString())

        })
    }
}
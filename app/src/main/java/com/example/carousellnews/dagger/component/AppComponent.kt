package com.example.carousellnews.dagger.component

import com.example.carousellnews.MainActivity
import com.example.carousellnews.dagger.module.AppModule
import com.example.carousellnews.dagger.module.NetworkModule
import com.example.carousellnews.ui.news.NewsListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])

interface AppComponent {
    fun inject(activity: MainActivity)
}
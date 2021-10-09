package com.example.carousellnews.dagger.component

import com.example.carousellnews.NewsActivity
import com.example.carousellnews.dagger.module.AppModule
import com.example.carousellnews.dagger.module.NetworkModule
import com.example.carousellnews.dagger.module.viewModule.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, NetworkModule::class])

interface AppComponent {
    fun inject(activity: NewsActivity)
}
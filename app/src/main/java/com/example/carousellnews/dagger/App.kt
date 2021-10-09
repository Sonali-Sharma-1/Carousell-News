package com.example.carousellnews.dagger

import android.app.Application
import com.example.carousellnews.dagger.component.AppComponent
import com.example.carousellnews.dagger.component.DaggerAppComponent
import com.example.carousellnews.dagger.module.AppModule
import com.example.carousellnews.dagger.module.NetworkModule

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(this))
            .build()
    }
}
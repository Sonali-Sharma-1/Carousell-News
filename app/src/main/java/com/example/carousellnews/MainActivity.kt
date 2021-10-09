package com.example.carousellnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carousellnews.dagger.App

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.appComponent.inject(this)

    }
}
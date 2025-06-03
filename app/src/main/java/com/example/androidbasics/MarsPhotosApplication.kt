package com.example.androidbasics

import android.app.Application
import com.example.androidbasics.data.AppContainer
import com.example.androidbasics.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
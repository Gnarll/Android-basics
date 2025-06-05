package com.example.androidbasics

import android.app.Application
import com.example.androidbasics.data.AppContainer
import com.example.androidbasics.data.DefaultAppContainer

class MainApplication : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer()
    }
}
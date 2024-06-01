package com.example.wordbook

import android.app.Application
import com.example.wordbook.di.AppComponent
import com.example.wordbook.di.DaggerAppComponent
import dagger.Component

class MainApplication: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

}
package com.example.wordbook.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.wordbook.MainActivity
import com.example.wordbook.data.local.WordDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): WordDatabase {
        val db = Room.databaseBuilder(context, WordDatabase::class.java, name = "words-app")
        Log.d(MainActivity.TAG, "db created")
        db.createFromAsset("words-app.db")
        return db.build()
    }
}
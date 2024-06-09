package com.example.wordbook.di

import android.content.Context
import android.content.SharedPreferences
import com.example.wordbook.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefsModule {
    @Singleton
    @Provides
    fun providePrefs(context: Context): SharedPreferences{
        return context.getSharedPreferences(context.getString(R.string.shared_prefs_file),0)
    }
}
package com.example.wordbook.di

import android.content.Context
import com.example.wordbook.ui.SettingsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPrefsModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun injectSettings(fragment: SettingsFragment)
}
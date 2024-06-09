package com.example.wordbook.viewmodels

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.wordbook.MainActivity.Companion.TAG
import com.example.wordbook.model.SettingsModel
import com.example.wordbook.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(val appPreferences: SharedPreferences) : ViewModel() {
    private val _uiState = MutableStateFlow<SettingsModel>(SettingsModel(false, false, false))

    val uiState: StateFlow<SettingsModel> = _uiState

    init {
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            _uiState.emit(
                SettingsModel(
                    appPreferences.getBoolean(Constants.NotifyWordOfTheDay, false),
                    appPreferences.getBoolean(Constants.DarkTheme, false),
                    appPreferences.getBoolean(Constants.History, false)
                )
            )
        }

//        _uiState.update {
//            it.copy(
//                notifyWordOfDay = appPreferences.getBoolean(Constants.NotifyWordOfTheDay, false),
//                darkTheme = appPreferences.getBoolean(Constants.DarkTheme, false),
//                history = appPreferences.getBoolean(Constants.History, false)
//            )
//        }
    }

    fun setDarkThemeSwitch(value: Boolean){
        appPreferences.edit {
            putBoolean(Constants.DarkTheme, value)
            commit()
        }
        refreshData()
    }

    fun setNotifyWordOfTheDaySwitch(value: Boolean){
        appPreferences.edit {
            putBoolean(Constants.NotifyWordOfTheDay, value)
            commit()
        }
        refreshData()
    }
    fun setHistorySwitch(value: Boolean){
        appPreferences.edit {
            putBoolean(Constants.History, value)
            commit()
        }
        refreshData()
    }

    companion object {
        class Factory(val appPreferences: SharedPreferences) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SettingsViewModel(appPreferences) as T
            }
        }
    }
}


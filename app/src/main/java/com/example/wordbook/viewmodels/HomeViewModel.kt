package com.example.wordbook.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wordbook.MainActivity.Companion.TAG
import com.example.wordbook.model.SearchItemModel
import com.example.wordbook.model.Word
import com.example.wordbook.model.WordRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(val wordRepository: WordRepository): ViewModel() {

    private val _wordsList: MutableStateFlow<List<Word>> = MutableStateFlow(listOf())
    private var searchJob: Job? = null


    // Public
    val wordsList = _wordsList.asStateFlow()

    fun updateSearchText(searchText: String){
        if(searchText.isEmpty()) return
        // Cancel old search Job
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(DEBOUNCE_PERIOD_MS)
            Log.d(TAG,"Repository request started for text $searchText")
            val result = wordRepository.getWordsStartingWith(searchText)
            Log.d(TAG, "THis is my latest data ${result.size} $result")
            _wordsList.emit(result)
        }
    }

    companion object {
        const val DEBOUNCE_PERIOD_MS = 200L
        class Factory(val wordRepository: WordRepository): ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(wordRepository) as T
            }
        }
    }
}
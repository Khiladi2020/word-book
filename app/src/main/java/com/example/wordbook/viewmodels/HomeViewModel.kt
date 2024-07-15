package com.example.wordbook.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wordbook.MainActivity.Companion.TAG
import com.example.wordbook.data.local.Word
import com.example.wordbook.data.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel(private val wordRepository: WordRepository): ViewModel() {

    private val _wordsList: MutableStateFlow<List<Word>> = MutableStateFlow(listOf())
    private var _lastWordId: Int? = null
    private var searchJob: Job? = null


    // Public
    val wordsList = _wordsList.asStateFlow()
    val lastWordId get() = _lastWordId

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _lastWordId = wordRepository.getMaxWordId()
            Log.d(TAG, "Last word id is $_lastWordId")
        }
    }

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

    fun getWordDetailById(id: Int): Flow<Word?> {
        return flow {
            val result = wordRepository.getWordWithId(id);
            emit(result)
        }
    }

    fun getRandomWord(): Flow<Word> {
        return flow {
            if (lastWordId != null){
                val randomWordId = Random.nextInt(0, lastWordId!!)
                val result = wordRepository.getWordWithId(randomWordId)
                if(result != null) emit(result)
                else emit(Word(0,"a","Error","Unexpected Error"))
            }
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
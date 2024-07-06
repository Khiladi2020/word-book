package com.example.wordbook.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wordbook.MainActivity.Companion.TAG
import com.example.wordbook.model.SearchItemModel
import com.example.wordbook.model.WordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(val wordRepository: WordRepository): ViewModel() {

    private val _wordsList: MutableStateFlow<List<SearchItemModel>> = MutableStateFlow(listOf())

    // Public
    val wordsList = _wordsList.asStateFlow()

    fun updateSearchText(searchText: String){
        viewModelScope.launch {
            Log.d(TAG,"Repository request started")
            val result = wordRepository.getWordsStartingWith(searchText).map { SearchItemModel(it.wordId ?: 0, it.word)}
            Log.d(TAG, "THis is my latest data $result")
            _wordsList.emit(result)
        }
    }

    companion object {
        class Factory(val wordRepository: WordRepository): ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(wordRepository) as T
            }
        }
    }
}
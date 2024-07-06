package com.example.wordbook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wordbook.model.SearchItemModel
import com.example.wordbook.model.WordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(wordRepository: WordRepository): ViewModel() {

    private val _wordsList: MutableStateFlow<List<SearchItemModel>> = MutableStateFlow(listOf())

    // Public
    val wordsList = _wordsList.asStateFlow()

    fun updateSearchText(searchText: String){
        _wordsList.update {
            it + listOf(SearchItemModel(2,"Hii"))
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
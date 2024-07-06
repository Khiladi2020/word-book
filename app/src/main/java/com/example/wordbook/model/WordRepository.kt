package com.example.wordbook.model

import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class WordRepository @Inject constructor(val database: WordDatabase) {
    suspend fun getAllWords(): List<Word> {
        return database.wordDao().getAll()
    }

    suspend fun getWordsStartingWith(alphabet: String = "A"): List<Word> {
        return database.wordDao().getAll()
    }
}
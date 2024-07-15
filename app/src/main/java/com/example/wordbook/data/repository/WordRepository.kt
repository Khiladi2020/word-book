package com.example.wordbook.data.repository

import com.example.wordbook.data.local.History
import com.example.wordbook.data.local.Word
import com.example.wordbook.data.local.WordDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WordRepository @Inject constructor(val database: WordDatabase) {
    suspend fun getAllWords(): List<Word> {
        return database.wordDao().getAll()
    }

    suspend fun getWordsStartingWith(alphabet: String = "A"): List<Word> {
        return withContext(Dispatchers.IO){
            database.wordDao().getWordsContainingText(alphabet)
        }
    }

    suspend fun getWordWithId(wordId: Int): Word? {
        return withContext(Dispatchers.IO){
            database.wordDao().getWordWithId(wordId)
        }
    }

    suspend fun getMaxWordId(): Int {
        return database.wordDao().getMaxWordId()
    }

    suspend fun insertHistory(historyItem: History){
        return withContext(Dispatchers.IO){
            database.historyDao().insertData(historyItem.text, historyItem.wordId)
        }
    }
}
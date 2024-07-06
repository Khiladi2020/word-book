package com.example.wordbook.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT * FROM `words-list`")
    fun getAll(): List<Word>

    @Query("SELECT * FROM `words-list` where word LIKE '%' || :text || '%'")
    fun getWordsContainingText(text: String): List<Word>
}
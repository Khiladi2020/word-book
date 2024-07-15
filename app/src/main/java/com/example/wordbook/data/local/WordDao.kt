package com.example.wordbook.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT * FROM `words-list`")
    fun getAll(): List<Word>

    @Query("SELECT * FROM `words-list` where word LIKE :text || '%' COLLATE NOCASE")
    fun getWordsContainingText(text: String): List<Word>

    @Query("SELECT * FROM `words-list` where word_id == :wordId")
    fun getWordWithId(wordId: Int): Word?

    @Query("SELECT MAX(word_id) FROM `words-list`")
    fun getMaxWordId(): Int
}
package com.example.wordbook.model

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
}
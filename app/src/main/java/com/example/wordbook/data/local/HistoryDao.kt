package com.example.wordbook.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {

    @Query("SELECT * from History")
    fun getAll(): List<History>

    @Query("INSERT INTO History (text, wordId) SELECT :text , :wordId WHERE NOT EXISTS (SELECT 1 from History WHERE wordId == :wordId) ")
    fun insertData(text: String, wordId: Int)
}
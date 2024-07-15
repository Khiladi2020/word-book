package com.example.wordbook.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Word::class, History::class], version = 1)
abstract class WordDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao

    abstract fun historyDao(): HistoryDao
}
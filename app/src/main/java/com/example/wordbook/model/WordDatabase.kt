package com.example.wordbook.model

import androidx.room.Database
import androidx.room.RoomDatabase
import java.util.ArrayList

@Database(entities = [Word::class], version = 1)
abstract class WordDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao
}
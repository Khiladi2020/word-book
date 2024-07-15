package com.example.wordbook.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val wordId: Int,
)
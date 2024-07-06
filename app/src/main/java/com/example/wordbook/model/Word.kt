package com.example.wordbook.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "words-list")
data class Word(
    @PrimaryKey @ColumnInfo(name = "word_id") val wordId: Int?,
    val letter: String,
    val word: String,
    val meaning: String
)
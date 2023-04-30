package com.example.hw18_2_dictionary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words_table")
data class WordEntity(
    @ColumnInfo(name = "persian_word") val persianWord: String,
    @ColumnInfo(name = "english_word") val englishWord: String,
    @ColumnInfo(name = "france_word") val frenchWord: String,
    @ColumnInfo(name = "arabic_word") val arabicWord: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)


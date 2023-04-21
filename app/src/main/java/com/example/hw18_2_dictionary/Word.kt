package com.example.hw18_2_dictionary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words_table")
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "farsi_word") val farsiWord: String,
    @ColumnInfo(name = "english_word") val englishWord: String
)

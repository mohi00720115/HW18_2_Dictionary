package com.example.hw18_2_dictionary

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface WordDao {

    @Query("SELECT * FROM words_table WHERE farsi_word LIKE :searchQuery OR english_word LIKE :searchQuery ORDER BY farsi_word ASC")
    fun searchWords(searchQuery: String): Flow<List<Word>>

    @Insert
    suspend fun insert(word: Word)

    @Update
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: Word)
}
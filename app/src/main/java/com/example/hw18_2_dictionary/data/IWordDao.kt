package com.example.hw18_2_dictionary.data

import androidx.room.*
import com.example.hw18_2_dictionary.model.Entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IWordDao {

    @Query(
        "SELECT * FROM words_table WHERE persian_word LIKE :searchQuery " +
                "OR english_word LIKE :searchQuery " +
                "OR france_word LIKE :searchQuery " +
                "OR arabic_word LIKE :searchQuery " +
                "ORDER BY persian_word ASC"
    )
    fun searchWords(searchQuery: String): Flow<List<WordEntity>>

    @Insert
    suspend fun insert(word: WordEntity)

    @Update
    suspend fun update(word: WordEntity)

    @Delete
    suspend fun delete(word: WordEntity)

    @Query("SELECT * FROM words_table")
    fun getAll() : Flow<List<WordEntity>>

    @Query("SELECT * FROM words_table WHERE id = :id")
    fun getWord(id : Int) : WordEntity

//    @Query("SELECT COUNT(*) FROM words_table")
//    fun countItem(): Flow<Int>

}
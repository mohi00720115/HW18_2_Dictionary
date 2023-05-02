package com.example.hw18_2_dictionary.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw18_2_dictionary.model.Entity.WordEntity

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): IWordDao
}

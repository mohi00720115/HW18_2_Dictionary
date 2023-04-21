package com.example.hw18_2_dictionary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val database = WordDatabase.getDatabase(application)
    private val wordDao = database.wordDao()

//    val allWords: LiveData<List<Word>> = wordDao.getAll().asLiveData()

    fun searchWords(searchQuery: String): LiveData<List<Word>> {
        return wordDao.searchWords("%$searchQuery%").asLiveData()
    }

    suspend fun insert(word: Word) {
        withContext(Dispatchers.IO) {
            wordDao.insert(word)
        }
    }

    suspend fun update(word: Word) {
        withContext(Dispatchers.IO) {
            wordDao.update(word)
        }
    }

    suspend fun delete(word: Word) {
        withContext(Dispatchers.IO) {
            wordDao.delete(word)
        }
    }

//    fun getWordCount(): LiveData<Int> {
//        return wordDao.getWordCount().asLiveData()
//    }

}
package com.example.hw18_2_dictionary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.example.hw18_2_dictionary.data.IWordDao
import com.example.hw18_2_dictionary.model.ui.WordData
import com.example.hw18_2_dictionary.others.asWordEntity
import com.example.hw18_2_dictionary.others.asWordUi
import com.example.hw18_2_dictionary.others.toWordUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(private val iWordDao: IWordDao) {


    fun searchWord(search: String): Flow<List<WordData>> {
        return iWordDao.searchWords("%$search%").map { wordEntity ->
            wordEntity.toWordUi()
        }
    }

    suspend fun insert(word: WordData) {
        return iWordDao.insert(word.asWordEntity())
    }

    suspend fun update(word: WordData) {
        return iWordDao.update(word.asWordEntity())
    }

    suspend fun delete(word: WordData) {
        iWordDao.delete(word.asWordEntity())
    }

    fun getAllWord(): Flow<List<WordData>> {
        return iWordDao.getAll().map { wordEntity ->
            wordEntity.toWordUi()
        }
    }

    fun getWord(id: Int): WordData {
        return iWordDao.getWord(id).asWordUi()
    }

//    fun countWord(): Flow<Int> {
//        return dictionaryDao.countItem()
//    }

}
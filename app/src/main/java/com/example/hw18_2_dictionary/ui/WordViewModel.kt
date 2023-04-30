package com.example.hw18_2_dictionary.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw18_2_dictionary.WordData
import com.example.hw18_2_dictionary.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _languageState = MutableLiveData<LanguageStateEnum>()
    val languageState: LiveData<LanguageStateEnum> = _languageState

    private val _wordList = MutableLiveData<List<WordData>>()
    val wordList: LiveData<List<WordData>> = repository.getAllWord()


    fun searchWord(search: String): LiveData<List<WordData>> {
        return repository.searchWord(search)
    }

    fun changeLanguage(languageState: LanguageStateEnum) {
        _languageState.postValue(languageState)
    }

    fun getAllWord() {
        viewModelScope.launch(Dispatchers.IO) {
            _wordList.postValue(repository.getAllWord().value)
        }
    }

    fun getWord(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getWord(id)
        }
    }

    fun delete(word: WordData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(word)
        }
    }


    fun insert(word: WordData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(word)
        }
    }

    fun update(word: WordData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(word)
        }
    }

//    fun getWordCount(): LiveData<Int> {
//        return wordDao.getWordCount().asLiveData()
//    }

}
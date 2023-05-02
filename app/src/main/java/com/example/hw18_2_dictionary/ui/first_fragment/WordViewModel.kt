package com.example.hw18_2_dictionary.ui.first_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw18_2_dictionary.model.ui.WordData
import com.example.hw18_2_dictionary.repository.Repository
import com.example.hw18_2_dictionary.ui.LanguageStateEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _languageState = MutableLiveData<LanguageStateEnum>()
    val languageState: LiveData<LanguageStateEnum> = _languageState

    private val _wordList = MutableLiveData<List<WordData>>()
    val wordList: LiveData<List<WordData>> = _wordList

    private val _countWord = MutableLiveData<Int>()
    val countWord: LiveData<Int> = _countWord

    private val _word = MutableLiveData<WordData>()
    val word: LiveData<WordData> = _word


    fun searchWord(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchWord(search).collect { words ->
                _wordList.postValue(words)
            }
        }
    }

    fun insert(word: WordData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(word)
        }
    }

    fun getInsert(enWord: String, prWord: String, frWord: String, arWord: String) {
        insert(WordData(enWord, prWord, frWord, arWord))
    }

    fun update(word: WordData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(word)
        }
    }

    fun delete(word: WordData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(word)
        }
    }

//    fun getAllWord() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _wordList.postValue(repository.getAllWord().value)
//        }
//    }

    fun getWord(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _word.postValue(repository.getWord(id))
        }
    }

    fun changeLanguage(languageState: LanguageStateEnum) {
        _languageState.postValue(languageState)
    }

//    fun getWordCount(): LiveData<Int> {
//        return wordDao.getWordCount().asLiveData()
//    }


//    fun checkCount() {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.countWord().collect { count ->
//                _countWord.postValue(count)
//            }
//        }
//    }

    init {
        searchWord("")
//        checkCount()
    }

}
package com.example.hw18_2_dictionary.others

fun checkWordsValid(enWord: String, prWord: String, frWord: String, arWord: String): Boolean {
    return !(enWord.isBlank() || prWord.isBlank() || frWord.isBlank() || arWord.isBlank())
}
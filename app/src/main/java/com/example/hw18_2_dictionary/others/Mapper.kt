package com.example.hw18_2_dictionary.others

import com.example.hw18_2_dictionary.WordData
import com.example.hw18_2_dictionary.model.WordEntity

/**
 *این میاد بصورت یک لیست اطلاعات دیتابیسمونو می گیره و خروجیش رو تبدیل می کنه بصورت یک لیست از دیتاکلاس جدیدمون
 */
fun List<WordEntity>.toWordUi(): List<WordData> =
    this.map { wordEntity ->
        wordEntity.asWordUi()
    }

/**
 * برای اینکه ما گاهی بخوایم اون اطلاعاتی که از دیتا کلاسمون که مال دیتابیسه بگیریم و یه سری کارهای جدید باهاش کنیم میایم دیتا کلاسی جدید می سازیم و به این طریق اونا رو بهم تبدیل می کنیم
 */
fun WordEntity.asWordUi(): WordData {
    return WordData(persianWord, englishWord, frenchWord, arabicWord, id)
}

/**
 * برای اینکه ما گاهی بخوایم اون اطلاعاتی که از دیتا کلاسمون که مال دیتابیسه بگیریم و یه سری کارهای جدید باهاش کنیم میایم دیتا کلاسی جدید می سازیم و به این طریق اونا رو بهم تبدیل می کنیم
 */
fun WordData.asWordEntity(): WordEntity {
    return WordEntity(persianWord, englishWord, frenchWord, arabicWord, id)
}



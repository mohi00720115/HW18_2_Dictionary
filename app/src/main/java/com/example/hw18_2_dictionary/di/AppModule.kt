package com.example.hw18_2_dictionary.di

import android.app.Application
import androidx.room.Room
import com.example.hw18_2_dictionary.data.IWordDao
import com.example.hw18_2_dictionary.data.WordDatabase
import com.example.hw18_2_dictionary.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDictionaryDatabase(application: Application) : WordDatabase{
        return Room.databaseBuilder(application,WordDatabase::class.java,"dictionary_database").build()
    }

    @Provides
    @Singleton
    fun provideWordDao(wordDatabase: WordDatabase) : IWordDao{
        return wordDatabase.wordDao()
    }

    @Provides
    @Singleton
    fun provideRepository(iWordDao: IWordDao):Repository {
        return Repository()
    }
}
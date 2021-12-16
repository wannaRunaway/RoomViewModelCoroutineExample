package com.example.myroomsample.repo

import androidx.annotation.WorkerThread
import com.example.myroomsample.db.Word
import com.example.myroomsample.db.WordDao
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }

    @WorkerThread
    suspend fun deleteAll(){
        wordDao.deleteAll()
    }
}
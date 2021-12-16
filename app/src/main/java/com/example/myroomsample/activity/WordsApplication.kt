package com.example.myroomsample.activity

import android.app.Application
import com.example.myroomsample.db.WordRoomDatabase
import com.example.myroomsample.repo.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { WordRoomDatabase.getDatabase(this, scope = applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
}
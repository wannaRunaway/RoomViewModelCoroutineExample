package com.example.myroomsample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): WordRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).addCallback(WordDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class WordDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { wordRoomDatabase ->
                scope.launch {
                    populateDatabase(wordRoomDatabase.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            //deleteAll
            wordDao.deleteAll()
            //Add words
            wordDao.insert(Word("BE BRAVE DI"))
            wordDao.insert(Word("DO NOT GIVEUP"))
        }
    }
}
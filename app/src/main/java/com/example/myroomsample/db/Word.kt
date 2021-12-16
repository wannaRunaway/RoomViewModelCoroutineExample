package com.example.myroomsample.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "work_table")
data class Word(
    @ColumnInfo(name = "word")
    var word: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
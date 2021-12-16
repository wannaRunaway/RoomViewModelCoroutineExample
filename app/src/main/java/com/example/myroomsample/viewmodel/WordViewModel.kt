package com.example.myroomsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myroomsample.db.Word
import com.example.myroomsample.repo.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(private var repository: WordRepository) : ViewModel() {

    val allWords:LiveData<List<Word>> = repository.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    fun deleteAdd() = viewModelScope.launch {
        repository.deleteAll()
    }
}
package com.example.myroomsample.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myroomsample.adapter.WordListAdapter
import com.example.myroomsample.databinding.ActivityMainBinding
import com.example.myroomsample.db.Word
import com.example.myroomsample.viewmodel.WordViewModel
import com.example.myroomsample.viewmodel.WordViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val newWOrdActivityRequestCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var adapter = WordListAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        wordViewModel.allWords.observe(this, Observer { wordViewModel ->
            wordViewModel?.let {
                adapter.submitList(it)
            }
        })
        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this, NewWordActivity::class.java)
            startActivityForResult(intent, newWOrdActivityRequestCode)
        }
        binding.clear.setOnClickListener{
            wordViewModel.deleteAdd()
        }
    }

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newWOrdActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let {
                wordViewModel.insert(Word(it))
            }
        } else {
            Snackbar.make(binding.content, "没有数据返回哦", Snackbar.LENGTH_LONG).show()
        }
    }
}
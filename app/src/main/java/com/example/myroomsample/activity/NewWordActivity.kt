package com.example.myroomsample.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.example.myroomsample.R
import com.example.myroomsample.databinding.ActivityNewWordBinding

class NewWordActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNewWordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewWordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.editWord.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                Log.d("DI", binding.editWord.text.toString())
                replyIntent.putExtra(EXTRA_REPLY, binding.editWord.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object{
        const val EXTRA_REPLY = "com.example.android.wordlistsqp.REPLY"
    }
}
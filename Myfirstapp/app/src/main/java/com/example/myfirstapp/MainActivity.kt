package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfirstapp.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val wordList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonSaveWord.setOnClickListener {
            val word = binding.editText1.text.toString()
            if (word.isNotEmpty()) {
                wordList.add(word)
                binding.editText1.text.clear()
            }
        }

        binding.buttonClearWords.setOnClickListener {
            wordList.clear()
            binding.textView1.text = ""
        }

        binding.buttonShowWords.setOnClickListener {
            val words = wordList.joinToString(", ")
            binding.textView1.text = if (words.isEmpty()) "No words saved" else words
        }
    }
}

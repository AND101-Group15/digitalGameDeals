package com.example.digitalgamedeals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.digitalgamedeals.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            print("hello")
        }
    }
}
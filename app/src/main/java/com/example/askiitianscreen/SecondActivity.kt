package com.example.askiitianscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.askiitianscreen.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val choices = intent.getSerializableExtra("selected_option")
        binding.tvChoices.text = choices.toString()
    }
}
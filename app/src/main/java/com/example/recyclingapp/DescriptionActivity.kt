package com.example.recyclingapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DescriptionActivity : AppCompatActivity() {
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_screen)

        backButton = findViewById(R.id.back_button)


        backButton.setOnClickListener {
            finish()
        }
    }
}
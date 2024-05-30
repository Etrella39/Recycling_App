package com.example.recyclingapp

import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class BackButtonClick : AppCompatActivity() {
    private lateinit var backButton: Button

    fun back() {
        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            finish()
        }
    }

}
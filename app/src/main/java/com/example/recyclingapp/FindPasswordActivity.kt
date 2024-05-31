package com.example.recyclingapp


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FindPasswordActivity : AppCompatActivity() {
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.find_password_screen)

        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            finish()
        }



    }
}

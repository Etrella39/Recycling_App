package com.example.recyclingapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ShowPasswordActivity : AppCompatActivity() {

    private lateinit var okButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        okButton = findViewById(R.id.ok_but)


        okButton.setOnClickListener {
            finish()
        }
    }
}

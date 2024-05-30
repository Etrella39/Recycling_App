package com.example.recyclingapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Spinner

class SettingsActivity : AppCompatActivity() {

    private lateinit var screenModeSpinner: Spinner
    private lateinit var languageSpinner: Spinner
    private lateinit var backButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_screen)

        backButton = findViewById(R.id.back_button)
        screenModeSpinner = findViewById(R.id.my_spinner_screen_mode)
        languageSpinner = findViewById(R.id.my_spinner_language)


        backButton.setOnClickListener {
            finish()
        }

    }
}

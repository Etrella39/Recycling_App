package com.example.recyclingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Spinner

class SettingsActivity : AppCompatActivity() {

    private lateinit var screenModeSpinner: Spinner
    private lateinit var languageSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_screen)


        screenModeSpinner = findViewById(R.id.my_spinner_screen_mode)
        languageSpinner = findViewById(R.id.my_spinner_language)


    }
}

package com.example.recyclingapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import android.widget.Spinner
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isGone

class SettingsActivity : AppCompatActivity() {

    private lateinit var screenModeButton: RelativeLayout
    private lateinit var languageButton: RelativeLayout

    private lateinit var modeToggleList: RelativeLayout
    private lateinit var languageToggleList: RelativeLayout

    private lateinit var spinnerModeImage: ImageView
    private lateinit var spinnerLanguageImage: ImageView

    private lateinit var backButton: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_screen)

        backButton = findViewById(R.id.back_button)

        screenModeButton = findViewById(R.id.spinner_mode)
        languageButton = findViewById(R.id.spinner_language)

        modeToggleList = findViewById(R.id.setting_toggle_mode)
        languageToggleList = findViewById(R.id.setting_toggle_language)
        modeToggleList.visibility = View.GONE
        languageToggleList.visibility = View.GONE

        spinnerModeImage = findViewById(R.id.spinner_mode_image)
        spinnerLanguageImage = findViewById(R.id.spinner_language_image)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

        // Set click listener for back button





        screenModeButton.setOnClickListener {
            if (modeToggleList.visibility == View.GONE) {
                modeToggleList.visibility = View.VISIBLE
                spinnerModeImage.setBackgroundResource(R.drawable.setting_toggle_button_open)
            } else {
                modeToggleList.visibility = View.GONE
                spinnerModeImage.setBackgroundResource(R.drawable.setting_toggle_button)
            }

        }


        languageButton.setOnClickListener {
            if (modeToggleList.visibility == View.GONE) {
                languageToggleList.visibility = View.VISIBLE
                spinnerLanguageImage.setBackgroundResource(R.drawable.setting_toggle_button_open)
            } else {
                languageToggleList.visibility = View.GONE
                spinnerLanguageImage.setBackgroundResource(R.drawable.setting_toggle_button)
            }


        }



        backButton.setOnClickListener {
            finish()
        }





//        // Set up listener for screen mode spinner
//        screenModeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                when (position) {
//                    0 -> setTheme(AppCompatDelegate.MODE_NIGHT_NO, "light") // Light Mode
//                    1 -> setTheme(AppCompatDelegate.MODE_NIGHT_YES, "dark") // Dark Mode
//                    2 -> setTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, "system") // System Default
//                }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Do nothing
//            }
//        }
//
//        // Set up listener for language spinner
//        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                // Implement language change logic here
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Do nothing
//            }
//        }
//    }
//
//    private fun setTheme(mode: Int, theme: String) {
//        AppCompatDelegate.setDefaultNightMode(mode)
//        sharedPreferences.edit().putString("theme", theme).apply()
//        recreate() // Recreate activity to apply the new theme
    }

}

package com.example.recyclingapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Spinner
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {

    private val back = BackButtonClick()

    private lateinit var screenModeSpinner: Spinner
    private lateinit var languageSpinner: Spinner
    private lateinit var backButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_screen)

        // Initialize views
        backButton = findViewById(R.id.back_button)


        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

        // Set click listener for back button

        back.back()






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

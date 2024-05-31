package com.example.recyclingapp

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity


class SettingsActivity : AppCompatActivity() {

    private lateinit var screenModeButton: RelativeLayout
    private lateinit var languageButton: RelativeLayout

    private var isClickModeToggle = false
    private var isClickLgToggle = false

    private lateinit var backButton: Button

    private lateinit var sharedPreferences: SharedPreferences

    private val spinnerAni: Animation = AnimationUtils.loadAnimation(this, R.anim.spinner_animation)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settingLayout = findViewById<RelativeLayout>(R.id.settings_screen)
        setContentView(settingLayout)

        val slideIn: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_in)
        settingLayout.startAnimation(slideIn)


        backButton = findViewById(R.id.back_button)

        screenModeButton = findViewById(R.id.spinner_mode)
        languageButton = findViewById(R.id.spinner_language)


        val modeToggleList: RelativeLayout = findViewById(R.id.setting_toggle_mode)
        val languageToggleList: RelativeLayout = findViewById(R.id.setting_toggle_language)
        modeToggleList.visibility = View.GONE
        languageToggleList.visibility = View.GONE

        val spinnerModeImage: ImageView = findViewById(R.id.spinner_mode_image)
        val spinnerLanguageImage: ImageView = findViewById(R.id.spinner_language_image)


        screenModeButton.setOnClickListener {
            setToggleButton(modeToggleList, spinnerModeImage, isClickModeToggle)
            isClickModeToggle = !isClickModeToggle
            if (isClickLgToggle) {
                setToggleButton(languageToggleList, spinnerLanguageImage, true)
                isClickLgToggle = !isClickLgToggle
            }
        }
        
        languageButton.setOnClickListener {
            setToggleButton(languageToggleList, spinnerLanguageImage, isClickLgToggle)
            isClickLgToggle = !isClickLgToggle
            if (isClickModeToggle) {
                setToggleButton(modeToggleList, spinnerModeImage, true)
                isClickModeToggle = !isClickModeToggle
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

    private fun setToggleButton(list: RelativeLayout, image: ImageView, isclick: Boolean) {
        if (!isclick) {
            list.visibility = View.VISIBLE
            image.startAnimation(spinnerAni)
        } else {
            list.visibility = View.GONE
            image.startAnimation(spinnerAni)
        }
    }



}


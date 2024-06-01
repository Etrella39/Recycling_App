package com.example.recyclingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale


class SettingsActivity : AppCompatActivity() {

    private lateinit var screenModeButton: RelativeLayout
    private lateinit var languageButton: RelativeLayout

    private var isClickModeToggle = false
    private var isClickLgToggle = false

    private lateinit var backButton: Button

    private lateinit var modeToggleList: RelativeLayout
    private lateinit var languageToggleList: RelativeLayout

    private lateinit var spinnerModeImage: ImageView
    private lateinit var spinnerLanguageImage: ImageView

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var settingLayout: RelativeLayout
    private lateinit var slideIn: Animation
    private lateinit var slideOut: Animation
    private lateinit var toggleOpen: Animation

    private lateinit var toggleModeLight: TextView
    private lateinit var toggleModeDark: TextView
    private lateinit var toggleModeSystem: TextView

    private lateinit var toggleLanguageEnglish: TextView
    private lateinit var toggleLanguageKorean: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_screen)

        slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in)
        slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out)
        toggleOpen = AnimationUtils.loadAnimation(this, R.anim.toggle_open)

        settingLayout = findViewById(R.id.settings_screen)
        settingLayout.startAnimation(slideIn)


        backButton = findViewById(R.id.back_button)

        screenModeButton = findViewById(R.id.spinner_mode)
        languageButton = findViewById(R.id.spinner_language)


        modeToggleList = findViewById(R.id.setting_toggle_mode)
        languageToggleList = findViewById(R.id.setting_toggle_language)
        modeToggleList.visibility = View.GONE
        languageToggleList.visibility = View.GONE

        spinnerModeImage = findViewById(R.id.spinner_mode_image)
        spinnerLanguageImage = findViewById(R.id.spinner_language_image)



        screenModeButton.setOnClickListener {
            setToggleButton(modeToggleList, spinnerModeImage)
            isClickModeToggle = !isClickModeToggle
        }

        languageButton.setOnClickListener {
            setToggleButton(languageToggleList, spinnerLanguageImage)
            isClickLgToggle = !isClickLgToggle
        }


        toggleModeLight = modeToggleList.findViewById(R.id.toggle_mode_light)
        toggleModeDark = modeToggleList.findViewById(R.id.toggle_mode_dark)
        toggleModeSystem = modeToggleList.findViewById(R.id.toggle_mode_system)

        toggleLanguageEnglish = findViewById(R.id.toggle_language_english)
        toggleLanguageKorean = findViewById(R.id.toggle_language_korean)


        toggleModeLight.setOnClickListener {
            if (AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_NO) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        toggleModeDark.setOnClickListener {
            if (AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
        toggleModeSystem.setOnClickListener {
            if (AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }

        toggleLanguageEnglish.setOnClickListener {
            setLocale("en")
        }
        toggleLanguageKorean.setOnClickListener {
            setLocale("ko")
        }




        backButton.setOnClickListener {
            settingLayout.startAnimation(slideOut)
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


    private fun setToggleButton(list: RelativeLayout, image: ImageView) {
        if (list.visibility == View.GONE) {
            list.visibility = View.VISIBLE
            rotateView(image, 0f, 180f)
        } else {
            list.visibility = View.GONE
            rotateView(image, 180f, 360f)
        }
    }

    private fun rotateView(view: View, fromDegrees: Float, toDegrees: Float) {
        val rotate = RotateAnimation(fromDegrees, toDegrees,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 200
        rotate.fillAfter = true
        view.startAnimation(rotate)
    }

    private fun setLocale(lang: String) {
        val myLocale = Locale(lang)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.setLocale(myLocale)

        res.updateConfiguration(conf, dm)
        val refresh = Intent(this, MainActivity::class.java)
        startActivity(refresh)
        finish()
    }


}


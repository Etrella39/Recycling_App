package com.example.recyclingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var settingButton: Button
    private lateinit var profile: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        settingButton = findViewById(R.id.setting_button)
        profile = findViewById(R.id.blogs_button)


        settingButton.setOnClickListener {
            Log.d("HomeActivity", "Setting button clicked")
            val intent = Intent(this@HomeActivity, SettingsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0); // non animation
        }
        profile.setOnClickListener() {
            val intent = Intent(this@HomeActivity, UserProfileActivity::class.java)
            startActivity(intent)
        }
    }
}

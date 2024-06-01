package com.example.recyclingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class UserProfileActivity : AppCompatActivity() {


    private lateinit var user_photo : FrameLayout
    private lateinit var settingButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)


        user_photo = findViewById(R.id.user_photo_button)
        settingButton = findViewById(R.id.setting_button)

        user_photo.setOnClickListener() {
            val intent = Intent(this@UserProfileActivity, PhotoActivity::class.java)
            startActivity(intent)
        }
        settingButton.setOnClickListener {
            Log.d("HomeActivity", "Setting button clicked")
            val intent = Intent(this@UserProfileActivity, SettingsActivity::class.java)
            startActivity(intent)

        }

    }
}

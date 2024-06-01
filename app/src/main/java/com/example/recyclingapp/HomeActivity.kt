package com.example.recyclingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var homeLayout: LinearLayout

    private lateinit var navigationBar: RelativeLayout
    private lateinit var settingButton: Button

    private lateinit var mainButton: RelativeLayout
    private lateinit var profileButton: LinearLayout
    private lateinit var blogButton: LinearLayout

    private lateinit var trashcanImage: ImageView
    private lateinit var fadeIn: Animation
    private lateinit var fadeOut: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        homeLayout = findViewById(R.id.home_screen_1)
        homeLayout.startAnimation(fadeIn)

        settingButton = findViewById(R.id.setting_button)

        navigationBar = findViewById(R.id.navigation_bar)
        mainButton = navigationBar.findViewById(R.id.main_button)
        profileButton = navigationBar.findViewById(R.id.profile_button)
        blogButton = navigationBar.findViewById(R.id.blogs_button)

        trashcanImage = mainButton.findViewById(R.id.trashcan_image)

        mainButton.setBackgroundResource(R.drawable.main_button_press)
        trashcanImage.setBackgroundResource(R.drawable.main_icon_trashcan_press)

        settingButton.setOnClickListener {
            Log.d("HomeActivity", "Setting button clicked")
            val intent = Intent(this@HomeActivity, SettingsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0); // non animation
        }
        profileButton.setOnClickListener {
            val intent = Intent(this@HomeActivity, UserProfileActivity::class.java)
            startActivity(intent)
            homeLayout.startAnimation(fadeOut)
        }
        blogButton.setOnClickListener {
            homeLayout.startAnimation(fadeOut)
        }

    }
}

package com.example.recyclingapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class HomeActivity : AppCompatActivity() {

    private lateinit var homeLayout: LinearLayout

    private lateinit var settingButton: Button

    private lateinit var navigationBar: RelativeLayout
    private lateinit var mainButton: RelativeLayout
    private lateinit var profileButton: LinearLayout
    private lateinit var blogButton: LinearLayout

    private lateinit var trashcanImage: ImageView

    private lateinit var buttonPaper: RelativeLayout


    private lateinit var fadeIn: Animation
    private lateinit var fadeOut: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen_2)

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        homeLayout = findViewById(R.id.home_screen_1)
        homeLayout.startAnimation(fadeIn)
        overridePendingTransition(0, 0); // non animation

        settingButton = findViewById(R.id.setting_button)

        navigationBar = findViewById(R.id.navigation_bar)
        mainButton = navigationBar.findViewById(R.id.main_button)
        profileButton = navigationBar.findViewById(R.id.profile_button)
        blogButton = navigationBar.findViewById(R.id.blogs_button)

        trashcanImage = mainButton.findViewById(R.id.trashcan_image)

        mainButton.setBackgroundResource(R.drawable.main_button_press)
        trashcanImage.setBackgroundResource(R.drawable.main_icon_trashcan_press)


        buttonPaper = findViewById(R.id.home_paper)



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
            finish()
        }
        blogButton.setOnClickListener {
            val intent = Intent(this@HomeActivity, BlogsActivity::class.java)
            startActivity(intent)
            homeLayout.startAnimation(fadeOut)
            finish()
        }


        buttonPaper.setOnClickListener {

            val intent = Intent(this@HomeActivity, DescriptionActivity::class.java)
            startActivity(intent)

        }



    }
}


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

class PhotoActivity : AppCompatActivity() {

    private lateinit var back_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile_photo)

        back_button = findViewById(R.id.back_button)

        back_button.setOnClickListener() {
            finish()
        }

    }
}

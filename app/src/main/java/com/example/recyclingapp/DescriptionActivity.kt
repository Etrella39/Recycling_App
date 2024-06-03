package com.example.recyclingapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class DescriptionActivity : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var descriptionBack: ScrollView

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_screen)

        descriptionBack = findViewById(R.id.description_back)

        val whatClicked = intent.getStringExtra("Description_KEY") ?: "paper"
        println(whatClicked)

//        when (whatClicked) {
//            "can" -> descriptionBack.set(R.layout.description_screen_paper)
//            "glass" -> descriptionBack.setBackgroundResource(R.layout.description_screen_paper)
//            "pet" -> descriptionBack.setBackgroundResource(R.layout.description_screen_paper)
//            "vinyl" -> descriptionBack.setBackgroundResource(R.layout.description_screen_paper)
//            "styro" -> descriptionBack.setBackgroundResource(R.layout.description_screen_paper)
//            else -> descriptionBack.setBackgroundResource(R.layout.description_screen_paper)
//        }

        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            finish()
        }
    }
}
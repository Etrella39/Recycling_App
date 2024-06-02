package com.example.recyclingapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class DescriptionActivity : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var descriptionBack: ImageView

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_screen)

        descriptionBack = findViewById(R.id.description_back)

        val whatClicked = intent.getStringExtra("Description_KEY") ?: "paper"
        println(whatClicked)

        when (whatClicked) {
            "can" -> descriptionBack.setImageDrawable(getDrawable(R.drawable.description_can_back))
            "glass" -> descriptionBack.setImageDrawable(getDrawable(R.drawable.description_glass_back))
            "pet" -> descriptionBack.setImageDrawable(getDrawable(R.drawable.description_pet_back))
            "vinyl" -> descriptionBack.setImageDrawable(getDrawable(R.drawable.description_vinyl_back))
            "styro" -> descriptionBack.setImageDrawable(getDrawable(R.drawable.description_styroform_back))
            else -> descriptionBack.setImageDrawable(getDrawable(R.drawable.description_paper_back))
        }

        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            finish()
        }
    }
}
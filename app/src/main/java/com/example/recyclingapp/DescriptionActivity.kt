package com.example.recyclingapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity

class DescriptionActivity : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var descriptionBack: ScrollView

    private lateinit var parentView: RelativeLayout

    private lateinit var layoutParams: RelativeLayout.LayoutParams
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_screen)

        descriptionBack = findViewById(R.id.description_back)

        parentView = findViewById(R.id.description_screen)

        parentView.removeView(descriptionBack)


        val whatClicked = intent.getStringExtra("Description_KEY") ?: "paper"
        println(whatClicked)

        layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )



        when (whatClicked) {
            "paper" -> setScrollScreen(R.layout.description_screen_paper)
            "can" -> setScrollScreen(R.layout.description_screen_can)
            "glass" -> setScrollScreen(R.layout.description_screen_glass)
            "pet" -> setScrollScreen(R.layout.description_screen_pet)
            "vinyl" -> setScrollScreen(R.layout.description_screen_vinyl)
            "styro" -> setScrollScreen(R.layout.description_screen_styrofoam)
            else -> setScrollScreen(R.layout.description_screen_paper)
        }

        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            finish()
        }
    }

    fun setScrollScreen(layout: Int) {
        val newView = LayoutInflater.from(this).inflate(layout, parentView, false)
        layoutParams.addRule(RelativeLayout.BELOW, R.id.back_button_2)
        newView.layoutParams = layoutParams
        parentView.addView(newView)
    }
}
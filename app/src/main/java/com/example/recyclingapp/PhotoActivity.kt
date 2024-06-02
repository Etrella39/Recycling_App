package com.example.recyclingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class PhotoActivity : AppCompatActivity() {

    private lateinit var backButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile_photo)
        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener() {
            finish()
        }

        findViewById<ImageButton>(R.id.character1).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_2) }
        findViewById<ImageButton>(R.id.character2).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_4) }
        findViewById<ImageButton>(R.id.character3).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_5) }
        findViewById<ImageButton>(R.id.character4).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_6) }
        findViewById<ImageButton>(R.id.character5).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_7) }
        findViewById<ImageButton>(R.id.character6).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_8) }
        findViewById<ImageButton>(R.id.character7).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_9) }
        findViewById<ImageButton>(R.id.character8).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_10) }
        findViewById<ImageButton>(R.id.character9).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_11) }
        findViewById<ImageButton>(R.id.character10).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_12) }
        findViewById<ImageButton>(R.id.character11).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_13) }
        findViewById<ImageButton>(R.id.character12).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_14) }
        findViewById<ImageButton>(R.id.character13).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_15) }
        findViewById<ImageButton>(R.id.character14).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_16) }
        findViewById<ImageButton>(R.id.character15).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_1) }
        findViewById<ImageButton>(R.id.character16).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_3) }
        findViewById<ImageButton>(R.id.character17).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_17) }
        findViewById<ImageButton>(R.id.character18).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_18) }
        findViewById<ImageButton>(R.id.character19).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_19) }
        findViewById<ImageButton>(R.id.character20).setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_20) }
    }

    private fun onImageButtonClick(imageResourceId: Int) {
        val resultIntent = Intent()
        resultIntent.putExtra("selectedImageResourceId", imageResourceId)
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}

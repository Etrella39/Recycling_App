package com.example.recyclingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserProfileActivity : AppCompatActivity() {

    private lateinit var userMainPhoto: ImageView
    private lateinit var personPhoto: View
    private lateinit var settingButton: Button
    private lateinit var logout: TextView
    private lateinit var backButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)

        userMainPhoto = findViewById(R.id.profile_photo)
        personPhoto = findViewById(R.id.person_photo)
        settingButton = findViewById(R.id.setting_button)
        logout = findViewById(R.id.log_out)
        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener() {
            finish()
        }

        personPhoto.visibility = View.VISIBLE

        userMainPhoto.setOnClickListener {
            val intent = Intent(this@UserProfileActivity, PhotoActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SELECT_PHOTO)
        }

        settingButton.setOnClickListener {
            val intent = Intent(this@UserProfileActivity, SettingsActivity::class.java)
            startActivity(intent)
        }

        logout.setOnClickListener {
            val intent = Intent(this@UserProfileActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_PHOTO && resultCode == RESULT_OK) {
            val selectedImageResourceId = data?.getIntExtra("selectedImageResourceId", -1)
            if (selectedImageResourceId != null && selectedImageResourceId != -1) {
                userMainPhoto.setImageResource(selectedImageResourceId)
                // Hide personPhoto once a user selects a profile photo
                personPhoto.visibility = View.INVISIBLE
            }
        }
    }

    companion object {
        const val REQUEST_CODE_SELECT_PHOTO = 1001
    }
}

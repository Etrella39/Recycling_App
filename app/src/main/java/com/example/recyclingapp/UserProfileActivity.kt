package com.example.recyclingapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserProfileActivity : AppCompatActivity() {

    private lateinit var userLayout: RelativeLayout

    private lateinit var userMainPhoto: ImageView
    private lateinit var personPhoto: View

    private lateinit var navigationBar: RelativeLayout
    private lateinit var mainButton: RelativeLayout
    private lateinit var profileButton: LinearLayout
    private lateinit var blogButton: LinearLayout

    private lateinit var customerImage: ImageView
    private lateinit var customerText: TextView

    private lateinit var settingButton: Button
    private lateinit var logout: TextView

    private lateinit var fadeIn: Animation
    private lateinit var fadeOut: Animation

    private lateinit var dbHelper: DBHelper
    private lateinit var deleteAccount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)

        dbHelper = DBHelper(this)

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        userLayout = findViewById(R.id.user_profile_1)
        overridePendingTransition(0, 0) // non animation

        userLayout.startAnimation(fadeIn)

        userMainPhoto = findViewById(R.id.profile_photo)
        personPhoto = findViewById(R.id.person_photo)
        settingButton = findViewById(R.id.setting_button)
        logout = findViewById(R.id.log_out)
        deleteAccount = findViewById(R.id.delete)

        navigationBar = findViewById(R.id.navigation_bar)
        mainButton = navigationBar.findViewById(R.id.main_button)
        profileButton = navigationBar.findViewById(R.id.profile_button)
        blogButton = navigationBar.findViewById(R.id.blogs_button)

        customerImage = findViewById(R.id.customer)
        customerImage.setBackgroundResource(R.drawable.main_icon_customer_press)
        customerText = findViewById(R.id.profile)
        customerText.setTextColor(Color.parseColor("#547E38"))

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
            val intent = Intent(this, LogOutDialogue::class.java)
            startActivity(intent)
        }

        deleteAccount.setOnClickListener {
            val intent = Intent(this, DeleteDialog::class.java)
            startActivity(intent)
        }

        mainButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
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

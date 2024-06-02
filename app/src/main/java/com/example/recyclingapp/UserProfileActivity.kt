package com.example.recyclingapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

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
    private lateinit var userName : TextView
    private lateinit var joinedDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)

        val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)

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
        userName = findViewById(R.id.user_name)
        joinedDateTextView = findViewById(R.id.joined_date)


        mainButton = findViewById(R.id.main_button)
        profileButton = findViewById(R.id.profile_button)

        blogButton = findViewById(R.id.blogs_button)
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
            overridePendingTransition(0, 0); // non animation
        }

        logout.setOnClickListener {
            val intent = Intent(this, LogOutDialogue::class.java)
            startActivity(intent)
        }


        deleteAccount.setOnClickListener {
            val userId = getCurrentUserId(auto) // Replace with actual method to fetch the current user ID
            Log.d("UserProfileActivity", "Attempting to delete user with ID: $userId")
            val intent = Intent(this, DeleteDialog::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        mainButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        blogButton.setOnClickListener {
            val intent = Intent(this@UserProfileActivity, BlogsActivity::class.java)
            startActivity(intent)
            finish()
        }


        userName.text = getCurrentUserId(auto)

        //fetch
        val userId = getCurrentUserId(auto)
        if (userId != null) {
            val joinedDate = dbHelper.getJoinedDate(userId)
            if (joinedDate != null) {
                joinedDateTextView.text = joinedDate
            }
        }

        if (userId != null) {
            val UserPhoto = dbHelper.getUserPhoto(userId)
            if (UserPhoto != 0) {
                userMainPhoto.setImageResource(UserPhoto)
                personPhoto.visibility = View.INVISIBLE
            }
        }

    }

    private fun getCurrentUserId(auto: SharedPreferences): String? {
        return auto.getString("userId", null)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_PHOTO && resultCode == RESULT_OK) {
            val selectedImageResourceId = data?.getIntExtra("selectedImageResourceId", -1)
            if (selectedImageResourceId != null && selectedImageResourceId != -1) {
                userMainPhoto.setImageResource(selectedImageResourceId)
                personPhoto.visibility = View.INVISIBLE
            }
        }
    }


    companion object {
        const val REQUEST_CODE_SELECT_PHOTO = 1001
    }
}

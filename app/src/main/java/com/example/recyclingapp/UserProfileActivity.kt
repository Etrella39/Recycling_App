package com.example.recyclingapp

import KeyEventHelper
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.yourapp.ReuseButton

class UserProfileActivity : AppCompatActivity() {

    private lateinit var userLayout: RelativeLayout
    private lateinit var userMainPhoto: ImageView
    private lateinit var personPhoto: View

    private lateinit var profileButton: LinearLayout

    private lateinit var customerImage: ImageView
    private lateinit var customerText: TextView

    private lateinit var settingButton: Button

    private lateinit var logout: TextView

    private lateinit var dbHelper: DBHelper
    private lateinit var deleteAccount: TextView
    private lateinit var userName : TextView
    private lateinit var joinedDateTextView: TextView

    private val REQUEST_CODE_SELECT_PHOTO = 1
    private val REQUEST_CODE_DELETE_PHOTO = 2

    private lateinit var keyEventHelper: KeyEventHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)

        keyEventHelper = KeyEventHelper(this)

        userLayout = findViewById(R.id.user_profile_1)

        val reuse = ReuseButton()
        reuse.bottomNavBar(this, findViewById(R.id.navigation_bar), "user")
        reuse.settingButton(this, findViewById(R.id.setting_button_2))
        AnimationFadeIn(this, userLayout)

        val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)

        dbHelper = DBHelper(this)

        userMainPhoto = findViewById(R.id.profile_photo)
        personPhoto = findViewById(R.id.person_photo)
        settingButton = findViewById(R.id.setting_button)
        logout = findViewById(R.id.log_out)
        deleteAccount = findViewById(R.id.delete)
        userName = findViewById(R.id.user_name)
        joinedDateTextView = findViewById(R.id.joined_date)


        profileButton = findViewById(R.id.profile_button)
        customerImage = findViewById(R.id.customer)

        customerImage.setBackgroundResource(R.drawable.main_icon_customer_press)
        customerText = findViewById(R.id.profile)
        customerText.setTextColor(Color.parseColor("#547E38"))


        userMainPhoto.setOnClickListener {
            val intent = Intent(this@UserProfileActivity, PhotoActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SELECT_PHOTO)
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
            isPhoto(userId)
        }

    }

    private fun getCurrentUserId(auto: SharedPreferences): String? {
        return auto.getString("userId", null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_PHOTO && resultCode == RESULT_OK) {
            val selectedImageId = data?.getIntExtra("selectedImageId", -1)
            val userID = data?.getStringExtra("userID")

            if (userID != null) {
                isPhoto(userID)
            }

            else if (selectedImageId != null && selectedImageId != -1) {
                userMainPhoto.setImageResource(selectedImageId)
                personPhoto.visibility = View.INVISIBLE
            }
        }

    }

    private fun isPhoto(userId: String) {
        val userPhoto = dbHelper.getUserPhoto(userId)
        if (userPhoto != 0) {
            userMainPhoto.setImageResource(userPhoto)
            personPhoto.visibility = View.INVISIBLE
        } else {
            userMainPhoto.setImageResource(R.drawable.user_profile_main)
            personPhoto.visibility = View.VISIBLE
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return keyEventHelper.handleOnKeyDown(keyCode, event)
    }

}

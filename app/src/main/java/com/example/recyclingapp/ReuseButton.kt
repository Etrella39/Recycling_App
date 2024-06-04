// ReuseButton.kt
package com.example.yourapp

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclingapp.BlogsActivity
import com.example.recyclingapp.HomeCameraActivity
import com.example.recyclingapp.R
import com.example.recyclingapp.SettingsActivity
import com.example.recyclingapp.UserProfileActivity

class ReuseButton() {

    fun bottomNavBar(activity: AppCompatActivity, view: View, string: String) {
        val mainButton: RelativeLayout = view.findViewById(R.id.main_button)
        val blogButton: LinearLayout = view.findViewById(R.id.blogs_button)
        val profileButton: LinearLayout = view.findViewById(R.id.profile_button) // 프로필 버튼 추가

        mainButton.setOnClickListener {
            if (string != "home") {
                val intent = Intent(activity, HomeCameraActivity::class.java)
                activity.startActivity(intent)
                activity.finish()
            }
        }

        blogButton.setOnClickListener {
            if (string != "blog") {
                val intent = Intent(activity, BlogsActivity::class.java)
                activity.startActivity(intent)
                activity.finish()
            }
        }

        profileButton.setOnClickListener {
            if (string != "user") {
                val intent = Intent(activity, UserProfileActivity::class.java)
                activity.startActivity(intent)
                activity.finish()
            }
        }
    }

    fun settingButton(activity: AppCompatActivity, view: View) {
        val settingButton: Button = view.findViewById(R.id.setting_button)

        settingButton.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            activity.startActivity(intent)
            activity.overridePendingTransition(0, 0); // non animation
        }

    }
    fun backButton(activity: AppCompatActivity, view: View) {
        val backButton: Button = view.findViewById(R.id.back_button)

        backButton.setOnClickListener {
            activity.finish()
        }
    }

    fun changeImage(view: View) {
        val trashcanImage: ImageView = view.findViewById(R.id.trashcan_image)
        view.setBackgroundResource(R.drawable.main_button_press)
        trashcanImage.setBackgroundResource(R.drawable.main_icon_trashcan_press)
    }
}

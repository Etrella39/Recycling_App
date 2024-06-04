package com.example.recyclingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclingapp.databinding.ActivityPhotoBinding
import com.example.yourapp.ReuseButton

class PhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoBinding

    private lateinit var dbHelper: DBHelper
    private lateinit var deletePhoto: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reuse = ReuseButton()
        reuse.backButton(this, findViewById(R.id.back_button_3))

        dbHelper = DBHelper(this)
        val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)

        deletePhoto = findViewById(R.id.delete_photo)


        binding.character1.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_2) }
        binding.character2.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_4) }
        binding.character3.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_5) }
        binding.character4.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_6) }
        binding.character5.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_7) }
        binding.character6.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_8) }
        binding.character7.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_9) }
        binding.character8.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_10) }
        binding.character9.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_11) }
        binding.character10.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_12) }
        binding.character11.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_13) }
        binding.character12.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_14) }
        binding.character13.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_15) }
        binding.character14.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_16) }
        binding.character15.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_1) }
        binding.character16.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_3) }
        binding.character17.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_17) }
        binding.character18.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_18) }
        binding.character19.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_19) }
        binding.character20.setOnClickListener { onImageButtonClick(R.drawable.user_profile_photo_20) }



        deletePhoto.setOnClickListener {
            val userId = auto.getString("userId", null)
            if (userId != null) {
                val userPhoto = dbHelper.getUserPhoto(userId)
                if (userPhoto != 0) {
                    dbHelper.deleteUserPhoto(userId)
                    val intent = Intent()
                    intent.putExtra("userID", userId)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }

    }

    @SuppressLint("CommitPrefEdits")
    private fun onImageButtonClick(imageResourceId: Int) {
        val intent = Intent()
        intent.putExtra("selectedImageId", imageResourceId)
        setResult(RESULT_OK, intent)

        val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)
        val userId = auto.getString("userId", null)

        if (userId != null) {
            dbHelper.addUserPhoto(userId, imageResourceId)
        }

        finish()
    }
}

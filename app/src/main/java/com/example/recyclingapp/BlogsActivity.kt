package com.example.recyclingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BlogsActivity : AppCompatActivity() {


    private lateinit var mainButton: RelativeLayout
    private lateinit var profileButton: LinearLayout
    private lateinit var blogButton: LinearLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.database_screen)

        mainButton = findViewById(R.id.main_button)

        profileButton = findViewById(R.id.profile_button)
        blogButton = findViewById(R.id.blogs_button)




        profileButton.setOnClickListener {
            val intent = Intent(this@BlogsActivity, UserProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        mainButton.setOnClickListener {
            val intent = Intent(this@BlogsActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}
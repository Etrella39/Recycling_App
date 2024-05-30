package com.example.recyclingapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class HomeActivity: Activity() {

    private lateinit var setting_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)


        setting_button = findViewById(R.id.setting_button)

        setting_button.setOnClickListener {
            val intent = Intent(this@HomeActivity, SetttigsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}

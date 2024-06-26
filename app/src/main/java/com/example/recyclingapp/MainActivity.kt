package com.example.recyclingapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.welcome_screen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.welcome_screen)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)
        val userID = auto.getString("userId", null)
        val passwordNo = auto.getString("userPass", null)

        val sharedPreferences = getSharedPreferences("appPreferences", MODE_PRIVATE)

        loadModeSetting(sharedPreferences)

        val hand = Handler()
        hand.postDelayed(Runnable {
            if (userID != null && passwordNo != null) {
                val intent = Intent(this@MainActivity, HomeCameraActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }

        }, 2000)


    }
    private fun loadModeSetting(sharedPreferences: SharedPreferences) {
        val isMode = sharedPreferences.getString("settingMode", null)

        when (isMode) {
            "Light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "Dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "System" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }


}
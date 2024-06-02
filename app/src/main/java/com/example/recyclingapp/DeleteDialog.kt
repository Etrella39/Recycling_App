package com.example.recyclingapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.example.recyclingapp.DBHelper

class DeleteDialog : Activity() {

    private lateinit var yesButton: TextView
    private lateinit var noButton: TextView
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.delete_account)

        dbHelper = DBHelper(this)

        yesButton = findViewById(R.id.delete_yes_button)
        noButton = findViewById(R.id.delete_no_button)

        val userId = intent.getStringExtra("USER_ID")

        yesButton.setOnClickListener {
            if (userId != null) {
                val isDeleted = dbHelper.deleteUser(userId)
                if (isDeleted) {
                    Toast.makeText(this, "User account deleted successfully", Toast.LENGTH_SHORT).show()
                    clearAutoLoginPreferences()
                    navigateToLoginScreen()
                } else {
                    Toast.makeText(this, "Failed to delete user account", Toast.LENGTH_SHORT).show()
                }
            }

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            finish()
        }

        noButton.setOnClickListener {
            finish()
        }
    }

    private fun clearAutoLoginPreferences() {
        val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)
        val autoLoginEdit = auto.edit()
        autoLoginEdit.clear()
        autoLoginEdit.apply()
    }

    private fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}

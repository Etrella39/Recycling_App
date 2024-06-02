package com.example.recyclingapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView

class DeleteDialog : Activity() {

    private lateinit var yesButton: TextView
    private lateinit var noButton: TextView
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.delete_account)

        dbHelper = DBHelper(this)

        yesButton = findViewById(R.id.delete_yes_button)
        noButton = findViewById(R.id.delete_no_button)

        val userId = intent.getStringExtra("USER_ID")
        Log.d("DeleteDialog", "Received USER_ID: $userId")

        yesButton.setOnClickListener {
            if (userId != null) {
                Log.d("DeleteDialog", "Attempting to delete user with ID: $userId")
                val isDeleted = dbHelper.deleteUser(userId)
                if (isDeleted) {
                    Log.d("DeleteDialog", "User deleted successfully")

                    // Clear auto-login preferences if any
                    val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)
                    val autoLoginEdit = auto.edit()
                    autoLoginEdit.clear()
                    autoLoginEdit.apply()

                    // Redirect to LoginActivity
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                } else {
                    Log.d("DeleteDialog", "Failed to delete user")
                }
            } else {
                Log.d("DeleteDialog", "USER_ID is null")
            }
        }

        noButton.setOnClickListener {
            finish()
        }
    }
}

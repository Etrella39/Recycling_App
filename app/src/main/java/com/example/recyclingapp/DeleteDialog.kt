package com.example.recyclingapp

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
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

    private lateinit var dialogText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialogue_log_out)

        yesButton = findViewById(R.id.log_out_yes_button)
        noButton = findViewById(R.id.log_out_no_button)

        dbHelper = DBHelper(this)

        dialogText = findViewById(R.id.your_password)
        dialogText.text = getString(R.string.dialogue_delete)

        val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)
        val userId = auto.getString("userId", null)

        yesButton.setOnClickListener {
            if (userId != null) {
                val isDeleted = dbHelper.deleteUser(userId)
                if (isDeleted) {
                    Toast.makeText(this, R.string.delete_success, Toast.LENGTH_SHORT).show()
                    clearAutoLoginPreferences(auto)
                    navigateToLoginScreen()
                } else {
                    Toast.makeText(this, R.string.delete_fail, Toast.LENGTH_SHORT).show()
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

    private fun clearAutoLoginPreferences(auto: SharedPreferences) {
        val autoLoginEdit = auto.edit()
        autoLoginEdit.clear()
        autoLoginEdit.apply()
    }

    private fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }
}

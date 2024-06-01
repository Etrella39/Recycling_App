package com.example.recyclingapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShowPasswordActivity : AppCompatActivity() {

    private lateinit var okButton: RelativeLayout
    private lateinit var passwordTextView: EditText
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_password_dialog)

        okButton = findViewById(R.id.find_button_for_show_dialog)
        passwordTextView = findViewById(R.id.password_edit)
        dbHelper = DBHelper(this)

        val userId = intent.getStringExtra("USER_ID")
        if (userId != null) {
            showPassword(userId)
        }

        okButton.setOnClickListener {
            finish()
        }
    }

    private fun showPassword(userId: String) {
        val password = dbHelper.findPasswordByUserId(userId)
        passwordTextView.hint = password ?: "Password not found"
    }
}

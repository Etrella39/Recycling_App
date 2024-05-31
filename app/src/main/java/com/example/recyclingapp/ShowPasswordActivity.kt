package com.example.recyclingapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShowPasswordActivity : AppCompatActivity() {

    private lateinit var passwordTextView: TextView
    private lateinit var okButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_password_dialog)

        passwordTextView = findViewById(R.id.your_password_)
        okButton = findViewById(R.id.ok_but)

        val password = intent.getStringExtra("password")
        passwordTextView.text = password

        okButton.setOnClickListener {
            finish()
        }
    }
}

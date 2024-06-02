package com.example.recyclingapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.TextView


class LogOutDialogue : Activity() {

    private lateinit var yesButton: TextView
    private lateinit var noButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setContentView(R.layout.dialogue_log_out)

        yesButton = findViewById(R.id.log_out_yes_button)
        noButton = findViewById(R.id.log_out_no_button)

        yesButton.setOnClickListener {
            val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)
            val autoLoginEdit = auto.edit()
            autoLoginEdit.remove("userID")
            autoLoginEdit.remove("userPass")
            autoLoginEdit.apply()

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }

        noButton.setOnClickListener {
            finish()
        }
    }


}

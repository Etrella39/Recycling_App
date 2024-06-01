package com.example.recyclingapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.Window
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment


class ShowPasswordActivity : Activity() {

    private val loginActivity = LoginActivity()

    private lateinit var okButton: RelativeLayout
    private lateinit var passwordTextView: EditText
    private lateinit var dbHelper: DBHelper

    private lateinit var pwShowButton: LinearLayout
    private var isPasswordVisible = false

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setContentView(R.layout.show_password_dialog)

        okButton = findViewById(R.id.find_button_for_show_dialog)
        passwordTextView = findViewById(R.id.password_edit)
        dbHelper = DBHelper(this)

        val userId = intent.getStringExtra("USER_ID")
        if (userId != null) {
            showPassword(userId)
        }

        pwShowButton = findViewById(R.id.password_icon)


        okButton.setOnClickListener {
            finish()
        }

        pwShowButton.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            loginActivity.ShowPassword(passwordTextView, isPasswordVisible, this)
        }

    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_OUTSIDE) {
            return false
        }
        return true
    }


    private fun showPassword(userId: String) {
        val password = dbHelper.findPasswordByUserId(userId)
        passwordTextView.setText(password)
    }
}

package com.example.recyclingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    var signup_btn: TextView? = null // 회원가입 버튼
    var login_btn: LinearLayout? = null // 로그인 버튼

    var id_edit: EditText? = null // id 에디트
    var pw_edit: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        signup_btn = findViewById<View>(R.id.signup_button) as TextView // 회원가입 버튼을 찾고
        login_btn = findViewById<View>(R.id.login_button) as LinearLayout

        id_edit = findViewById<View>(R.id.id_edit) as EditText // id 에디트를 찾음.
        pw_edit = findViewById<View>(R.id.password_edit) as EditText

        signup_btn!!.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
        login_btn!!.setOnClickListener {
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

}

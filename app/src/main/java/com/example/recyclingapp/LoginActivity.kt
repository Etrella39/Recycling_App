package com.example.recyclingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.view.MotionEvent;
import android.widget.ImageButton;
import androidx.annotation.Nullable;

class LoginActivity : AppCompatActivity() {
    lateinit var SignUp_btn: TextView // 회원가입 버튼
    lateinit var LogIn_btn: LinearLayout // 로그인 버튼

    lateinit var ID_edit: EditText // id 에디트
    lateinit var PW_edit: EditText

    lateinit var PW_Showbutton: View // 비밀번호 확인 버튼

    var DB:DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        DB = DBHelper(this)

        SignUp_btn = findViewById(R.id.signup_button)
        LogIn_btn = findViewById(R.id.login_button)

        ID_edit = findViewById(R.id.id_edit)
        PW_edit = findViewById(R.id.password_edit)

        PW_Showbutton = findViewById(R.id.password_icon)

        LogIn_btn.setOnClickListener {
            val user = ID_edit.text.toString()
            val pass = PW_edit.text.toString()

            // 빈칸 제출시 Toast
            if (user == "" || pass == "") {
                Toast.makeText(this@LoginActivity, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                val checkUserpass = DB!!.checkUserpass(user, pass)
                // id 와 password 일치시
                if (checkUserpass) {
                    Toast.makeText(this@LoginActivity, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    Toast.makeText(this@LoginActivity, "아이디와 비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        SignUp_btn.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        PW_Showbutton.setOnClickListener {
            ShowPassword()
        }

    }

}

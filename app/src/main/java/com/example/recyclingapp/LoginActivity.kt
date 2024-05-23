package com.example.recyclingapp

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class LoginActivity : AppCompatActivity() {
    private lateinit var signUpBTN: TextView // 회원가입 버튼
    private lateinit var loginBTN: LinearLayout // 로그인 버튼

    private lateinit var idEdit: EditText // id 에디트
    private lateinit var pwEdit: EditText

    private lateinit var pwShowbutton: LinearLayout // 비밀번호 확인 버튼
    var isPasswordVisible = false

    var DB:DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        DB = DBHelper(this)

        signUpBTN = findViewById(R.id.signup_button)
        loginBTN = findViewById(R.id.login_button)

        idEdit = findViewById(R.id.id_edit)
        pwEdit = findViewById(R.id.password_edit)

        pwShowbutton = findViewById(R.id.password_icon)

        val filter = InputFilter { source, start, end, dest, dstart, dend ->
            val pattern = Regex("[^a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};:\"\\\\|,.<>/?]")
            if (pattern.containsMatchIn(source)) {
                "" // 영어, 숫자, 특수문자 이외의 입력 차단
            } else {
                null // 영어, 숫자, 특수문자 입력 허용
            }
        }

        pwEdit.filters = arrayOf(filter)


        loginBTN.setOnClickListener {
            val user = idEdit.text.toString()
            val pass = pwEdit.text.toString()

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

        signUpBTN.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        pwShowbutton.setOnClickListener {
            ShowPassword()
        }

    }

    fun ShowPassword () {
        isPasswordVisible = !isPasswordVisible
        pwEdit.inputType = if (isPasswordVisible) {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        pwEdit.typeface = ResourcesCompat.getFont(this, R.font.interbold)
        pwEdit.setSelection(pwEdit.length()); // 커서를 끝에 위치시키기

    }

}

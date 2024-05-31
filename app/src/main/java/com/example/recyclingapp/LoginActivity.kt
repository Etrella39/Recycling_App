package com.example.recyclingapp

import android.content.Context
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
    private lateinit var find_password : TextView   //findPassword

    private lateinit var idEdit: EditText // id 에디트
    private lateinit var pwEdit: EditText

    private lateinit var pwShowButton: LinearLayout // 비밀번호 확인 버튼
    private var isPasswordVisible = false

    var DB:DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        DB = DBHelper(this)

        signUpBTN = findViewById(R.id.signup_button)
        loginBTN = findViewById(R.id.login_button)
        find_password = findViewById(R.id.find_passwo)

        idEdit = findViewById(R.id.id_edit)
        pwEdit = findViewById(R.id.password_edit)

        pwShowButton = findViewById(R.id.password_icon)

        val filter = InputFilter { source, start, end, dest, dstart, dend ->
            val pattern = Regex("[^a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};:\"\\\\|,.<>/?]")
            val lengthFilter = InputFilter.LengthFilter(15)

            if (pattern.containsMatchIn(source)) {
                "" // 영어, 숫자, 특수문자 이외의 입력 차단
            } else if (lengthFilter.filter(source, start, end, dest, dstart, dend) != null) {
                "" // 길이 제한 초과 시 차단
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
            finish()
        }

        //find your password
        find_password.setOnClickListener {
            val intent = Intent(this@LoginActivity, FindPasswordActivity::class.java)
            startActivity(intent)
        }

        pwShowButton.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            ShowPassword(pwEdit, isPasswordVisible, this)
        }

    }

    fun ShowPassword(_pwEdit: EditText, PasswordVisible: Boolean, _context: Context) {
        _pwEdit.inputType = if (PasswordVisible) {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        _pwEdit.typeface = ResourcesCompat.getFont(_context, R.font.interbold)
        _pwEdit.setSelection(_pwEdit.length()); // 커서를 끝에 위치시키기

    }

}

package com.example.recyclingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity() {

    private val loginActivity = LoginActivity()

    var DB:DBHelper?=null
    private lateinit var idEdit: EditText
    lateinit var pwEdit: EditText
    private lateinit var confirmPassword: LinearLayout
    private lateinit var confirmPasswordView: EditText

    private lateinit var SignUpButton: LinearLayout

    private lateinit var pwShowButton: LinearLayout
    private lateinit var pwShowButton2: LinearLayout
    private var isPasswordVisible = false
    private var isPasswordVisible2 = false

    private lateinit var backButton: Button

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_screen)

        DB = DBHelper(this)
        idEdit = findViewById(R.id.id_edit)
        pwEdit = findViewById(R.id.password_edit)

        confirmPassword = findViewById(R.id.confirm_password)
        confirmPasswordView = confirmPassword.findViewById(R.id.password_edit)
        confirmPasswordView.hint = getString(R.string.confirm_password)

        SignUpButton = findViewById(R.id.sign_up_button)

        pwShowButton = findViewById(R.id.password_icon)
        pwShowButton2 = confirmPassword.findViewById(R.id.password_icon)

        backButton = findViewById(R.id.back_button)

        val filter = InputFilter { source, start, end, dest, dstart, dend ->
            val pattern = Regex("[^a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};:\"\\\\|,.<>/?]")
            val lengthFilter = LengthFilter(15)

            if (pattern.containsMatchIn(source)) {
                "" // 영어, 숫자, 특수문자 이외의 입력 차단
            } else if (lengthFilter.filter(source, start, end, dest, dstart, dend) != null) {
                "" // 길이 제한 초과 시 차단
            } else {
                null // 영어, 숫자, 특수문자 입력 허용
            }
        }

        pwEdit.filters = arrayOf(filter)
        confirmPasswordView.filters = arrayOf(filter)

        SignUpButton.setOnClickListener {
            val user = idEdit.text.toString()
            val pass = pwEdit.text.toString()
            val repass = confirmPasswordView.text.toString()

            val idPattern = "^(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6,15}$"
            val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9!@#\$%^&*()_+\\-=\\[\\]{};:\"\\\\|,.<>/?]{8,15}$"

            // 사용자 입력이 비었을 때
            if (user == "" || pass == "" || repass == "")
                Toast.makeText(this@SignUpActivity, R.string.all_info, Toast.LENGTH_SHORT).show()
            else {
                // 아이디 형식이 맞을 때
                if (Pattern.matches(idPattern, user)) {
                    val checkUsername = DB!!.checkUser(user)
                    // 비밀번호 형식이 맞을 때
                    if (Pattern.matches(pwPattern, pass)) {
                        // 비밀번호 재확인 성공
                        if (pass == repass) {
                            if (checkUsername == false) {
                                val insert = DB!!.insertData(user, pass)
                                // 가입 성공 시 Toast를 띄우고 메인 화면으로 전환
                                if (insert == true) {
                                    loginActivity.saveUserId(user, pass, this)
                                    Toast.makeText(this@SignUpActivity, R.string.register, Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this, HomeActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this@SignUpActivity, R.string.signup_fail, Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(this@SignUpActivity, R.string.exist_id, Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this@SignUpActivity, R.string.password_not_match, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@SignUpActivity, R.string.password_try_again, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@SignUpActivity, R.string.id_try_again, Toast.LENGTH_SHORT).show()
                }
            }
        }

        pwShowButton.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            loginActivity.ShowPassword(pwEdit, isPasswordVisible, this)
        }

        pwShowButton2.setOnClickListener {
            isPasswordVisible2 = !isPasswordVisible2
            loginActivity.ShowPassword(confirmPasswordView, isPasswordVisible2, this)
        }

        backButton.setOnClickListener {
            finish()
        }

    }



}

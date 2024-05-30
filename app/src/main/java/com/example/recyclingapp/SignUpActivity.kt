package com.example.recyclingapp

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.AllCaps
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
    private lateinit var confirmPassword: EditText

    private lateinit var SignUpBotton: LinearLayout
    private lateinit var pwShowbutton: LinearLayout
    private lateinit var pwShowbutton2: LinearLayout// 비밀번호 확인 버튼
    private var isPasswordVisible = false
    private var isPasswordVisible2 = false

    private val back = BackButtonClick()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_screen)

        DB = DBHelper(this)
        idEdit = findViewById(R.id.id_edit)
        pwEdit = findViewById(R.id.password_edit)
        confirmPassword = findViewById(R.id.confirm_password)

        SignUpBotton = findViewById(R.id.sign_up_button)

        pwShowbutton = findViewById(R.id.password_icon)
        pwShowbutton2 = findViewById(R.id.password_icon2)

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
        confirmPassword.filters = arrayOf(filter)

        SignUpBotton.setOnClickListener {
            val user = idEdit.text.toString()
            val pass = pwEdit.text.toString()
            val repass = confirmPassword.text.toString()

            val idPattern = "^(?=.*[a-z])(?=.*[0-9])[A-Za-z[0-9]]{6,15}$"
            val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,15}$"

            // 사용자 입력이 비었을 때
            if (user == "" || pass == "" || repass == "")
                Toast.makeText(this@SignUpActivity, "회원정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
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
                                    Toast.makeText(this@SignUpActivity, "가입되었습니다.", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this, HomeActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this@SignUpActivity, "가입에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(this@SignUpActivity, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this@SignUpActivity, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@SignUpActivity, "비밀번호는 영어와 숫자를 포함하여 8자 이상 입력해 주세요.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@SignUpActivity, "아이디는 영어와 숫자를 포함하여 6자 이상 입력해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        back.back()

        pwShowbutton.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            loginActivity.ShowPassword(pwEdit, isPasswordVisible, this)
        }
        pwShowbutton2.setOnClickListener {
            isPasswordVisible2 = !isPasswordVisible2
            loginActivity.ShowPassword(confirmPassword, isPasswordVisible2, this)
        }


    }



}

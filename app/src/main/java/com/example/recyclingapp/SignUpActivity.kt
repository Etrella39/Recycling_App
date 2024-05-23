package com.example.recyclingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity() {

    var DB:DBHelper?=null
    lateinit var ID_edit: EditText
    lateinit var PW_edit: EditText
    lateinit var ConfirmPassword: EditText

    lateinit var SignUpBotton: LinearLayout
    private lateinit var pwShowbutton: LinearLayout // 비밀번호 확인 버튼
    var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_screen)

        DB = DBHelper(this)
        ID_edit = findViewById(R.id.id_edit)
        PW_edit = findViewById(R.id.password_edit)
        ConfirmPassword = findViewById(R.id.confirm_password)

        SignUpBotton = findViewById(R.id.sign_up_button)

        pwShowbutton = findViewById(R.id.password_icon)

        val filter = InputFilter { source, start, end, dest, dstart, dend ->
            val pattern = Regex("[^a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};:\"\\\\|,.<>/?]")
            if (pattern.containsMatchIn(source)) {
                "" // 영어, 숫자, 특수문자 이외의 입력 차단
            } else {
                null // 영어, 숫자, 특수문자 입력 허용
            }
        }

        SignUpBotton.setOnClickListener {
            val user = ID_edit.text.toString()
            val pass = PW_edit.text.toString()
            val repass = ConfirmPassword.text.toString()

            val idPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,15}$"
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
                                    val intent = Intent(applicationContext, HomeActivity::class.java)
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
                        Toast.makeText(this@SignUpActivity, "비밀번호 형식이 옳지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@SignUpActivity, "아이디 형식이 옳지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        pwShowbutton.setOnClickListener {
        }

    }



}

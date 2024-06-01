package com.example.recyclingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FindPasswordActivity : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var findPasswordByIds: EditText  // textview
    private lateinit var findButtonId: RelativeLayout  // button for id
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.find_password_screen)

        backButton = findViewById(R.id.back_button)
        findPasswordByIds = findViewById(R.id.id_edit)
        findButtonId = findViewById(R.id.find_button)
        dbHelper = DBHelper(this)

        backButton.setOnClickListener {
            finish()
        }

        findButtonId.setOnClickListener {
            val userId = findPasswordByIds.text.toString()
            if (dbHelper.checkUser(userId)) {
                val intent = Intent(this, ShowPasswordActivity::class.java)
                intent.putExtra("USER_ID", userId)
                startActivity(intent)
            } else {
                Toast.makeText(this, "ID not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
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
    private lateinit var userIdEditText: EditText
    private lateinit var findPasswordButton: RelativeLayout
    private lateinit var dbHelper: DBHelper // Assuming DBHelper is your SQLiteOpenHelper class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.find_password_screen)

        backButton = findViewById(R.id.back_button)
        userIdEditText = findViewById(R.id.id_edit)
        findPasswordButton = findViewById(R.id.find_button)
        dbHelper = DBHelper(this)

//        // Set click listener for findPasswordButton
//        findPasswordButton.setOnClickListener {
//            val userId = userIdEditText.text.toString()
//            val password = dbHelper.findPasswordByUserId(userId)
//
//            if (password != null) {
//                // Password found, navigate to ShowPasswordActivity
//                val intent = Intent(this@FindPasswordActivity, ShowPasswordActivity::class.java)
//                intent.putExtra("password", password)
//                startActivity(intent)
//            } else {
//                // No password found for the given user ID
//                Toast.makeText(this, "Password not found for the given user ID", Toast.LENGTH_SHORT).show()
//            }
//        }

        // Set click listener for back button
        backButton.setOnClickListener {
            finish()

        }
    }

}


package com.example.recyclingapp

import KeyEventHelper
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.KeyEvent
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.yourapp.ReuseButton

class HomeCameraActivity : AppCompatActivity() {

    private lateinit var mainLayout: LinearLayout

    private lateinit var startButton: TextView
    private lateinit var tipsButton: TextView

    private val CAMERA_REQUEST_CODE = 100

    private lateinit var mainButton: RelativeLayout
    private lateinit var trashcanImage: ImageView

    private lateinit var keyEventHelper: KeyEventHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)
        mainLayout = findViewById(R.id.home_screen_1)

        keyEventHelper = KeyEventHelper(this)

        val reuse = ReuseButton()
        reuse.bottomNavBar(this, findViewById(R.id.navigation_bar), "home")
        reuse.settingButton(this, findViewById(R.id.setting_button_2))
        AnimationFadeIn(this, mainLayout)
        reuse.changeImage(findViewById(R.id.main_button))

        startButton = findViewById(R.id.start_button)
        tipsButton = findViewById(R.id.home_screen2_button)

        startButton.setOnClickListener {
            if (checkAndRequestPermissions()) {
                dispatchTakePictureIntent()
            }
        }
        tipsButton.setOnClickListener {
            val intent = Intent(this@HomeCameraActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun checkAndRequestPermissions(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val writeStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        val listPermissionsNeeded = mutableListOf<String>()

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (writeStoragePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), CAMERA_REQUEST_CODE)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    dispatchTakePictureIntent()
                } else {
                    Toast.makeText(this, "Camera and Storage permissions are required", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            val imageUri = data?.data
            // Handle the captured image
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return keyEventHelper.handleOnKeyDown(keyCode, event)
    }
}

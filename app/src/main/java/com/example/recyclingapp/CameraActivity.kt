package com.example.recyclingapp

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView

class CameraActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan_screen)

        // You can add functionality for the buttons here, for example, to handle the capture button click
        val captureButton = findViewById<ImageView>(R.id.capture_button)
        captureButton.setOnClickListener {
            // Handle capture button click
        }

        val changeDirectionButton = findViewById<ImageView>(R.id.change_direction_button)
        changeDirectionButton.setOnClickListener {
            // Handle change direction button click
        }
    }
}

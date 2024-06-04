package com.example.recyclingapp

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.yourapp.ReuseButton


class HomeActivity : AppCompatActivity() {

    private lateinit var homeLayout: LinearLayout

    private lateinit var navigationBar: RelativeLayout
    private lateinit var mainButton: RelativeLayout
    private lateinit var profileButton: LinearLayout
    private lateinit var blogButton: LinearLayout

    private lateinit var trashcanImage: ImageView

    private lateinit var buttonPaper: RelativeLayout
    private lateinit var buttonCan: RelativeLayout
    private lateinit var buttonGlass: RelativeLayout
    private lateinit var buttonPet: RelativeLayout
    private lateinit var buttonVinyl: RelativeLayout
    private lateinit var buttonStyro: RelativeLayout

    private lateinit var home_title : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen_2)
        homeLayout = findViewById(R.id.home_screen_1)

        val reuse = ReuseButton()
        reuse.bottomNavBar(this, findViewById(R.id.navigation_bar), "home2")
        reuse.settingButton(this, findViewById(R.id.setting_button_2))
        AnimationFadeIn(this, homeLayout)

        reuse.changeImage(findViewById(R.id.main_button))


        buttonPaper = findViewById(R.id.home_paper)
        buttonCan = findViewById(R.id.home_can)
        buttonGlass = findViewById(R.id.home_glass)
        buttonPet = findViewById(R.id.home_plastic)
        buttonVinyl = findViewById(R.id.home_vinvl)
        buttonStyro = findViewById(R.id.home_styrofoam)

        home_title = findViewById(R.id.home_title)
        home_title.setOnClickListener {
            val intent = Intent(this@HomeActivity, HomeCameraActivity::class.java)
            startActivity(intent)
        }


        buttonPaper.setOnClickListener {
            setDescription("paper")
        }
        buttonCan.setOnClickListener {
            setDescription("can")
        }
        buttonGlass.setOnClickListener {
            setDescription("glass")
        }
        buttonPet.setOnClickListener {
            setDescription("pet")
        }
        buttonVinyl.setOnClickListener {
            setDescription("vinyl")
        }
        buttonStyro.setOnClickListener {
            setDescription("styro")
        }

    }
    private fun setDescription(key: String) {
        val intent = Intent(this@HomeActivity, DescriptionActivity::class.java)
        intent.putExtra("Description_KEY", key)
        startActivity(intent)
    }

}


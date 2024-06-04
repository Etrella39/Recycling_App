package com.example.recyclingapp

import KeyEventHelper
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.yourapp.ReuseButton
import android.os.Process
import kotlin.system.exitProcess


class HomeActivity : AppCompatActivity() {

    private lateinit var homeLayout: LinearLayout

    private lateinit var buttonPaper: RelativeLayout
    private lateinit var buttonCan: RelativeLayout
    private lateinit var buttonGlass: RelativeLayout
    private lateinit var buttonPet: RelativeLayout
    private lateinit var buttonVinyl: RelativeLayout
    private lateinit var buttonStyro: RelativeLayout

    private lateinit var home_title : TextView

    private lateinit var keyEventHelper: KeyEventHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen_2)
        homeLayout = findViewById(R.id.home_screen_1)

        val reuse = ReuseButton()
        reuse.bottomNavBar(this, findViewById(R.id.navigation_bar), "home2")
        reuse.settingButton(this, findViewById(R.id.setting_button_2))
        AnimationFadeIn(this, homeLayout)

        keyEventHelper = KeyEventHelper(this)

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
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return keyEventHelper.handleOnKeyDown(keyCode, event)
    }
    private fun setDescription(key: String) {
        val intent = Intent(this@HomeActivity, DescriptionActivity::class.java)
        intent.putExtra("Description_KEY", key)
        startActivity(intent)
    }

}


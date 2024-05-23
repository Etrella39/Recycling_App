package com.example.recyclingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.view.MotionEvent;
import android.widget.ImageButton;
import androidx.annotation.Nullable;



class ShowPassword: AppCompatActivity() {

    lateinit var PW_edit: EditText
    lateinit var PW_show: View

    fun seePassword (_PW_show: View) {
        this.PW_show = _PW_show
    }


}
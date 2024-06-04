package com.example.recyclingapp

import android.app.Activity
import android.view.View
import android.view.animation.AnimationUtils

class AnimationFadeIn(private val activity: Activity, view: View) {
    val fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
    val fadeOut = AnimationUtils.loadAnimation(activity, R.anim.fade_out)

    init {
        activity.overridePendingTransition(0, 0) // non animation
        view.startAnimation(fadeIn)
    }


}
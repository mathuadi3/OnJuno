package com.adhish.onjuno.util

import android.R
import android.app.Activity
import android.graphics.drawable.PictureDrawable
import android.view.View
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.bumptech.glide.Glide


fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

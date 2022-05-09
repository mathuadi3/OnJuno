package com.adhish.onjuno.util

import android.view.View
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest


fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}


fun ImageView.getSVGFromUrl(url: String) {
    if (url.lowercase().endsWith("svg")) {
        val imageLoader = ImageLoader.Builder(context)
            .componentRegistry {
                add(SvgDecoder(context))
            }.build()

        val request = ImageRequest.Builder(context).apply {
            data(url).decoder(SvgDecoder(context))
        }.target(this).build()

        imageLoader.enqueue(request)
    }
}
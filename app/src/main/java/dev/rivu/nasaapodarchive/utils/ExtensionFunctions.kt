package dev.rivu.nasaapodarchive.utils

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}
fun View.isVisible() = visibility == View.VISIBLE
fun View.gone() {
    visibility = View.GONE
}
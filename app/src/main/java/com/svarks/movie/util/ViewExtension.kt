package com.svarks.movie.util

import android.view.View

/**
 * Extension function to show the view
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Extension function to hide the view
 */
fun View.hide() {
    this.visibility = View.GONE
}

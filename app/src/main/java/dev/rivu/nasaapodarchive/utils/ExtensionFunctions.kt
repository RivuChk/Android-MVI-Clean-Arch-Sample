package dev.rivu.nasaapodarchive.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import dev.rivu.nasaapodarchive.R
import dev.rivu.nasaapodarchive.domain.utils.format
import dev.rivu.nasaapodarchive.presentation.apodlist.model.ApodViewData

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.isVisible() = visibility == View.VISIBLE
fun View.gone() {
    visibility = View.GONE
}

fun ImageView.load(
    url: String,
    requestOptions: RequestOptions = RequestOptions.placeholderOf(R.drawable.ic_image_placeholder),
    onLoadingFinished: (isSuccess: Boolean) -> Unit = {}
) {
    val listener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished(false)
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished(true)
            return false
        }
    }
    Glide.with(this)
        .load(url)
        .apply(requestOptions)
        .listener(listener)
        .into(this)
}

operator fun List<ApodViewData>.get(date: String): ApodViewData? = find {
    it.date.format().equals(date, true)
}
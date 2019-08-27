package dev.rivu.nasaapodarchive.presentation.apodlist.model

import dev.rivu.nasaapodarchive.domain.model.APOD
import java.util.*

data class ApodViewData(
    val date: Date,
    val explanation: String,
    val mediaType: APOD.MediaType,
    val title: String,
    val url: String,
    val hdUrl: String? = null,
    val copyright: String? = null
) {
    val imageUrl: String by lazy {
        if (mediaType is APOD.MediaType.IMAGE) {
            if (!hdUrl.isNullOrBlank()) {
                hdUrl
            } else {
                url
            }
        } else {
            val regExp =
                "/^.*((youtu.be\\/)|(v\\/)|(\\/u\\/\\w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#\\&\\?]*).*/".toRegex()
            val groups = regExp.matchEntire(url)?.groupValues
            if (groups != null && groups.size > 7 && groups[7].length == 11) {
                groups[7]
            } else {
                ""
            }
        }
    }
}
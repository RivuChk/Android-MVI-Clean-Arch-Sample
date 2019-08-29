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
            url
        } else {
            val videoId = if(url.contains("embed/")) {
                url.substringAfter("embed/").substringBefore("?")
            } else {
                ""
            }

            if(videoId.isNullOrBlank()) {
                url
            } else {
                "https://img.youtube.com/vi/$videoId/0.jpg"
            }
        }
    }
}
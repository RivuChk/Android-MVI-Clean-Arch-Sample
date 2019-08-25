package dev.rivu.nasaapodarchive.domain.model

import java.util.Date

data class APOD (
    val date: Date,
    val explanation: String,
    val mediaType: MediaType,
    val title: String,
    val url: String,
    val hdUrl: String? = null,
    val copyright: String? = null
) {
    enum class MediaType(val value: String) {
        VIDEO("video"),
        IMAGE("image")
    }
}
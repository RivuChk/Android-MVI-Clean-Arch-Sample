package dev.rivu.nasaapodarchive.domain.model

data class APOD (
    val date: String,
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
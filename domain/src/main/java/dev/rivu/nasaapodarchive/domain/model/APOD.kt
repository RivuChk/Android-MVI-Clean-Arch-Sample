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
    sealed class MediaType(val value: String) {
        object VIDEO: MediaType("video")
        object IMAGE: MediaType("image")

        companion object {
            @JvmStatic
            fun valueOfIgnoringCase(str: String): MediaType {
                return if(str.equals(VIDEO.value, true)) {
                    VIDEO
                } else if(str.equals(IMAGE.value, true)) {
                    IMAGE
                } else {
                    throw IllegalArgumentException("No enum constant dev.rivu.nasaapodarchive.domain.model.APOD.MediaType.$str")
                }

            }
        }
    }
}
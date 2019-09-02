package dev.rivu.nasaapodarchive.presentation.apodlist.model

import android.os.Parcel
import android.os.Parcelable
import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.domain.utils.format
import dev.rivu.nasaapodarchive.domain.utils.parseDate
import kotlinx.android.parcel.Parcelize
import java.util.*

data class ApodPresenterData(
    val date: Date,
    val explanation: String,
    val mediaType: APOD.MediaType,
    val title: String,
    val url: String,
    val hdUrl: String? = null,
    val copyright: String? = null
): Parcelable {
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

    constructor(parcel: Parcel) : this(
        parcel.readString().parseDate(),
        parcel.readString(),
        APOD.MediaType.valueOfIgnoringCase(parcel.readString()),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date.format())
        parcel.writeString(explanation)
        parcel.writeString(mediaType.value)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(hdUrl)
        parcel.writeString(copyright)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApodPresenterData> {
        override fun createFromParcel(parcel: Parcel): ApodPresenterData {
            return ApodPresenterData(parcel)
        }

        override fun newArray(size: Int): Array<ApodPresenterData?> {
            return arrayOfNulls(size)
        }
    }
}
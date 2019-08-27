package dev.rivu.nasaapodarchive.presentation.apodlist.mapper

import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.domain.model.mapper.Mapper
import dev.rivu.nasaapodarchive.presentation.apodlist.model.ApodViewData

open class ApodViewMapper: Mapper<APOD, ApodViewData> {
    override fun mapFromDomain(type: APOD): ApodViewData {
        return ApodViewData(
            copyright = type.copyright,
            date = type.date,
            explanation = type.explanation,
            hdUrl = type.hdUrl,
            mediaType = type.mediaType,
            title = type.title,
            url = type.url
        )
    }

    override fun mapToDomain(type: ApodViewData): APOD {
        return APOD(
            copyright = type.copyright,
            date = type.date,
            explanation = type.explanation,
            hdUrl = type.hdUrl,
            mediaType = type.mediaType,
            title = type.title,
            url = type.url
        )
    }
}
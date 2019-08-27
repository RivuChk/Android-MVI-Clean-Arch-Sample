package dev.rivu.nasaapodarchive.remote.mapper

import dev.rivu.nasaapodarchive.domain.model.mapper.Mapper
import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.remote.model.ApodResponse
import dev.rivu.nasaapodarchive.utils.format
import dev.rivu.nasaapodarchive.utils.parseDate

open class ApodMapper: Mapper<APOD, ApodResponse> {
    override fun mapFromDomain(type: APOD): ApodResponse {
        return ApodResponse(
            copyright = type.copyright,
            date = type.date.format(),
            explanation = type.explanation,
            hdUrl = type.hdUrl,
            mediaType = type.mediaType.value,
            serviceVersion = "v1",
            title = type.title,
            url = type.url
        )
    }

    override fun mapToDomain(type: ApodResponse): APOD {
        return APOD(
            copyright = type.copyright,
            date = type.date.parseDate(),
            explanation = type.explanation,
            hdUrl = type.hdUrl,
            mediaType = APOD.MediaType.valueOfIgnoringCase(type.mediaType),
            title = type.title,
            url = type.url
        )
    }
}
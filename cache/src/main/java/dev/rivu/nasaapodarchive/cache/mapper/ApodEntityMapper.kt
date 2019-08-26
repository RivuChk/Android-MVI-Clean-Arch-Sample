package dev.rivu.nasaapodarchive.cache.mapper

import dev.rivu.nasaapodarchive.cache.model.ApodEntity
import dev.rivu.nasaapodarchive.data.mapper.Mapper
import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.utils.format
import dev.rivu.nasaapodarchive.utils.parseDate

open class ApodEntityMapper: Mapper<APOD, ApodEntity> {
    override fun mapFromDomain(type: APOD): ApodEntity {
        return ApodEntity(
            copyright = type.copyright,
            date = type.date.format(),
            explanation = type.explanation,
            hdUrl = type.hdUrl,
            mediaType = type.mediaType.value,
            title = type.title,
            url = type.url
        )
    }

    override fun mapToDomain(type: ApodEntity): APOD {
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
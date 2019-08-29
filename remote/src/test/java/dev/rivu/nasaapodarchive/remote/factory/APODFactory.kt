package dev.rivu.nasaapodarchive.remote.factory

import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.remote.model.ApodResponse
import dev.rivu.nasaapodarchive.domain.utils.parseDate

public object APODFactory {

    fun makeApodResponse(): ApodResponse {
        return ApodResponse(
            date = DataFactory.randomDate(),
            explanation = DataFactory.randomString(),
            mediaType = DataFactory.randomMediaType(),
            title = DataFactory.randomString(),
            url = DataFactory.randomString(),
            serviceVersion = ""
        )
    }

    fun makeApod(): APOD {
        return APOD(
            date = DataFactory.randomDate().parseDate(),
            explanation = DataFactory.randomString(),
            mediaType = APOD.MediaType.valueOfIgnoringCase(DataFactory.randomMediaType()),
            title = DataFactory.randomString(),
            url = DataFactory.randomString()
        )
    }
}
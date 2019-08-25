package dev.rivu.nasaapodarchive.domain.factory

import dev.rivu.nasaapodarchive.domain.model.APOD

object APODFactory {

    fun makeAPOD(): APOD {
        return APOD(
            date = DataFactory.randomDate(),
            explanation = DataFactory.randomString(),
            mediaType = DataFactory.randomMediaType(),
            title = DataFactory.randomString(),
            url = DataFactory.randomString()
        )
    }

    fun makeAPODs(count: Int = 10): List<APOD> {
        val apodList = mutableListOf<APOD>()
        repeat(count) {
            apodList.add(makeAPOD())
        }
        return apodList
    }
}
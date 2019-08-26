package dev.rivu.nasaapodarchive.cache.androidtest.factory

import dev.rivu.nasaapodarchive.cache.model.ApodEntity
import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.utils.parseDate

public object APODFactory {

    fun makeApodEntity(): ApodEntity {
        return ApodEntity(
            date = DataFactory.randomDate(),
            explanation = DataFactory.randomString(),
            mediaType = DataFactory.randomMediaType(),
            title = DataFactory.randomString(),
            url = DataFactory.randomString()
        )
    }

    fun makeApodEntities(count: Int): List<ApodEntity> {
        return (1 .. count).asIterable()
            .map {
                makeApodEntity()
            }
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

    fun makeApods(count: Int): List<APOD> {
        return (1 .. count).asIterable()
            .map {
                makeApod()
            }
    }
}
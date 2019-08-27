package dev.rivu.nasaapodarchive.presentation.test.factory

import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.presentation.apodlist.model.ApodViewData

public object APODFactory {

    fun makeApodView(): ApodViewData {
        return ApodViewData(
            date = DataFactory.randomDate(),
            explanation = DataFactory.randomString(),
            mediaType = DataFactory.randomMediaType(),
            title = DataFactory.randomString(),
            url = DataFactory.randomString()
        )
    }

    fun makeApodViews(count: Int): List<ApodViewData> {
        return (1 .. count).asIterable()
            .map {
                makeApodView()
            }
    }

    fun makeApod(): APOD {
        return APOD(
            date = DataFactory.randomDate(),
            explanation = DataFactory.randomString(),
            mediaType = DataFactory.randomMediaType(),
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
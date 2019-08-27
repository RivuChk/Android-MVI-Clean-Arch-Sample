package dev.rivu.nasaapodarchive.presentation.apodlist.mapper

import dev.rivu.nasaapodarchive.presentation.test.factory.APODFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ApodMapperTest {

    lateinit var apodMapper: ApodViewMapper

    @Before
    fun setUp() {
        apodMapper = ApodViewMapper()
    }

    @Test
    fun `mapFromDomain should return same data, type casted to APOD`() {
        val apodResponse = APODFactory.makeApodView()
        val apod = apodMapper.mapToDomain(apodResponse)

        assertEquals(apodResponse.copyright, apod.copyright)
        assertEquals(apodResponse.hdUrl, apod.hdUrl)
        assertEquals(apodResponse.date, apod.date)
        assertEquals(apodResponse.explanation, apod.explanation)
        assertEquals(apodResponse.mediaType, apod.mediaType)
        assertEquals(apodResponse.title, apod.title)
        assertEquals(apodResponse.url, apod.url)
    }

    @Test
    fun `mapToDomain should return same data, type casted to ApodResponse`() {
        val apod = APODFactory.makeApod()
        val apodResponse = apodMapper.mapFromDomain(apod)

        assertEquals(apodResponse.copyright, apod.copyright)
        assertEquals(apodResponse.hdUrl, apod.hdUrl)
        assertEquals(apodResponse.date, apod.date)
        assertEquals(apodResponse.explanation, apod.explanation)
        assertEquals(apodResponse.mediaType, apod.mediaType)
        assertEquals(apodResponse.title, apod.title)
        assertEquals(apodResponse.url, apod.url)
    }
}
package dev.rivu.nasaapodarchive.cache.mapper

import dev.rivu.nasaapodarchive.cache.test.factory.APODFactory
import dev.rivu.nasaapodarchive.domain.utils.format
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ApodMapperTest {

    lateinit var apodMapper: ApodEntityMapper

    @Before
    fun setUp() {
        apodMapper = ApodEntityMapper()
    }

    @Test
    fun `mapFromDomain should return same data, type casted to APOD`() {
        val apodResponse = APODFactory.makeApodEntity()
        val apod = apodMapper.mapToDomain(apodResponse)

        assertEquals(apodResponse.copyright, apod.copyright)
        assertEquals(apodResponse.hdUrl, apod.hdUrl)
        assertEquals(apodResponse.date, apod.date.format())
        assertEquals(apodResponse.explanation, apod.explanation)
        assertEquals(apodResponse.mediaType, apod.mediaType.value)
        assertEquals(apodResponse.title, apod.title)
        assertEquals(apodResponse.url, apod.url)
    }

    @Test
    fun `mapToDomain should return same data, type casted to ApodResponse`() {
        val apod = APODFactory.makeApod()
        val apodResponse = apodMapper.mapFromDomain(apod)

        assertEquals(apodResponse.copyright, apod.copyright)
        assertEquals(apodResponse.hdUrl, apod.hdUrl)
        assertEquals(apodResponse.date, apod.date.format())
        assertEquals(apodResponse.explanation, apod.explanation)
        assertEquals(apodResponse.mediaType, apod.mediaType.value)
        assertEquals(apodResponse.title, apod.title)
        assertEquals(apodResponse.url, apod.url)
    }
}
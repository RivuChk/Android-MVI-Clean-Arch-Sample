package dev.rivu.nasaapodarchive.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import dev.rivu.nasaapodarchive.data.factory.APODFactory
import dev.rivu.nasaapodarchive.data.repository.ApodCache

import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class ApodCachedDataStoreTest {
    private lateinit var mockApodCache: ApodCache
    private lateinit var apodCacheDataStore: ApodCachedDataStore

    @Before
    fun setup() {
        mockApodCache = mock()
        apodCacheDataStore = ApodCachedDataStore(mockApodCache)
    }

    @Test
    fun `getApod should ApodCache#getApod`() {
        apodCacheDataStore.getApod("2018-08-26")
        verify(mockApodCache, times(1)).getApod(anyString())
    }

    @Test
    fun `saveApods should ApodCache#saveApods`() {
        val apodList = APODFactory.makeAPODs(10)
        apodCacheDataStore.saveApods(apodList)
        verify(mockApodCache, times(1)).saveApods(apodList)
    }

    @Test
    fun `cleareApods should ApodCache#cleareApods`() {
        apodCacheDataStore.clearApods()
        verify(mockApodCache, times(1)).clearApods()
    }

    @Test
    fun `isCached should ApodCache#isCached`() {
        apodCacheDataStore.isCached()
        verify(mockApodCache, times(1)).isCached()
    }
}
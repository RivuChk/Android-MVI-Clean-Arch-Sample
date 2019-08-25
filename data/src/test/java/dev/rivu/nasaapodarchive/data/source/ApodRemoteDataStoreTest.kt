package dev.rivu.nasaapodarchive.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import dev.rivu.nasaapodarchive.data.factory.APODFactory
import dev.rivu.nasaapodarchive.data.repository.ApodRemote

import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class ApodRemoteDataStoreTest {
    private lateinit var mockApodRemote: ApodRemote
    private lateinit var apodRemoteDataStore: ApodRemoteDataStore

    @Before
    fun setup() {
        mockApodRemote = mock()
        apodRemoteDataStore = ApodRemoteDataStore(mockApodRemote)
    }

    @Test
    fun `getApod should ApodRemote#getApod`() {
        apodRemoteDataStore.getApod("2018-08-26")
        verify(mockApodRemote, times(1)).getApod(anyString())
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `saveApods should throw UnsupportedOperationException`() {
        apodRemoteDataStore.saveApods(APODFactory.makeAPODs(10))
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `cleareApods should throw UnsupportedOperationException`() {
        apodRemoteDataStore.clearApods()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `isCached should throw UnsupportedOperationException`() {
        apodRemoteDataStore.isCached()
    }
}
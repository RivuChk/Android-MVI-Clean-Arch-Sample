package dev.rivu.nasaapodarchive.data

import com.nhaarman.mockito_kotlin.*
import dev.rivu.nasaapodarchive.data.factory.APODFactory
import dev.rivu.nasaapodarchive.data.repository.ApodDataStore
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString

class APODListRepositoryTest {
    private lateinit var mockCachedDataStore: ApodDataStore
    private lateinit var mockRemoteDataStore: ApodDataStore
    private lateinit var aPODListRepositoryImpl: APODListRepositoryImpl

    @Before
    fun setup() {
        mockCachedDataStore = mock()
        mockRemoteDataStore = mock()
        aPODListRepositoryImpl = APODListRepositoryImpl(mockCachedDataStore, mockRemoteDataStore)
    }

    @Test
    fun `getApod should not call remote if cache returns data`() {
        stubCachedDataStoreSuccess()
        val date = "2018-08-26"

        aPODListRepositoryImpl.getApod(date).test()
        verify(mockCachedDataStore, times(1)).getApod(date)
        verify(mockRemoteDataStore, never()).getApod(anyString())
        verifyNoMoreInteractions(mockCachedDataStore)
        verifyNoMoreInteractions(mockRemoteDataStore)
    }

    @Test
    fun `getApod should call remote if cache emits error, it should also save data to cache`() {
        stubCachedDataStoreError()
        stubRemoteDataStoreSuccess()
        val date = "2018-08-26"

        aPODListRepositoryImpl.getApod(date).test()
        verify(mockCachedDataStore, times(1)).getApod(date)
        verify(mockRemoteDataStore, times(1)).getApod(anyString())
        verify(mockCachedDataStore, times(1)).saveApods(anyList())
        verifyNoMoreInteractions(mockCachedDataStore)
        verifyNoMoreInteractions(mockRemoteDataStore)
    }

    @Test
    fun `getApods should call cache n number of times, should not call remote, if cache returns data in all cases`() {
        stubCachedDataStoreSuccess()
        stubRemoteDataStoreSuccess()
        val date = "2018-08-26"

        aPODListRepositoryImpl.getApods(date, 10).test()
        verify(mockCachedDataStore, times(10)).getApod(anyString())
        verify(mockRemoteDataStore, never()).getApod(anyString())
        verifyNoMoreInteractions(mockCachedDataStore)
        verifyNoMoreInteractions(mockRemoteDataStore)
    }

    @Test
    fun `getApods should complete`() {
        stubCachedDataStoreSuccess()
        stubRemoteDataStoreSuccess()
        val date = "2018-08-26"

        val testSubscriber = aPODListRepositoryImpl.getApods(date, 10).test()
        testSubscriber.assertComplete()
    }

    fun stubCachedDataStoreSuccess() {
        whenever(mockCachedDataStore.getApod(anyString()))
            .thenReturn(Single.just(APODFactory.makeAPOD()))
    }

    fun stubCachedDataStoreError() {
        whenever(mockCachedDataStore.getApod(anyString()))
            .thenReturn(Single.error(Exception()))
    }

    fun stubRemoteDataStoreSuccess() {
        whenever(mockRemoteDataStore.getApod(anyString()))
            .thenReturn(Single.just(APODFactory.makeAPOD()))
    }

    fun stubRemoteDataStoreError() {
        whenever(mockRemoteDataStore.getApod(anyString()))
            .thenReturn(Single.error(Exception()))
    }
}
package dev.rivu.nasaapodarchive.data

import com.nhaarman.mockito_kotlin.*
import dev.rivu.nasaapodarchive.data.factory.APODFactory
import dev.rivu.nasaapodarchive.data.repository.ApodDataStore
import dev.rivu.nasaapodarchive.domain.model.APOD
import io.reactivex.Completable
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString

class APODListRepositoryTest {
    private lateinit var mockCachedDataStore: ApodDataStore
    private lateinit var mockRemoteDataStore: ApodDataStore
    private lateinit var aPODListRepositoryImpl: APODListRepositoryImpl

    val stubSuccessCacheResponse by lazy {
        APODFactory.makeAPOD()
    }
    val stubSuccessRemoteResponse by lazy {
        APODFactory.makeAPOD()
    }

    @Before
    fun setup() {
        mockCachedDataStore = mock()
        mockRemoteDataStore = mock()
        aPODListRepositoryImpl = APODListRepositoryImpl(mockCachedDataStore, mockRemoteDataStore)
    }

    @Test
    fun `getApod should not call remote if cache returns data`() {
        stubCachedDataStoreSuccess()
        stubRemoteDataStoreError()
        val date = "2018-08-26"

        val repositoryTestObserver = aPODListRepositoryImpl.getApod(date).test()
        verify(mockCachedDataStore, times(1)).getApod(date)
        verify(mockRemoteDataStore, times(1)).getApod(anyString())
        verifyNoMoreInteractions(mockCachedDataStore)
        verifyNoMoreInteractions(mockRemoteDataStore)
        repositoryTestObserver.assertValue(stubSuccessCacheResponse)
    }

    @Test
    fun `getApod should call remote if cache emits error, it should also save data to cache`() {
        stubCachedDataStoreError()
        stubRemoteDataStoreSuccess()
        whenever(mockCachedDataStore.saveApods(any())).thenReturn(Completable.complete())
        val date = "2018-08-26"

        val repositoryTestObserver = aPODListRepositoryImpl.getApod(date).test()
        verify(mockCachedDataStore, times(1)).getApod(date)
        verify(mockRemoteDataStore, times(1)).getApod(anyString())
        verify(mockCachedDataStore, times(1)).saveApods(anyList())
        verifyNoMoreInteractions(mockCachedDataStore)
        verifyNoMoreInteractions(mockRemoteDataStore)
        repositoryTestObserver.assertValue(stubSuccessRemoteResponse)
    }

    @Test
    fun `getApods should call cache n number of times, should not call remote, if cache returns data in all cases`() {
        stubCachedDataStoreSuccess()
        stubRemoteDataStoreSuccess()
        val date = "2018-08-26"

        val repositoryTestObserver = aPODListRepositoryImpl.getApods(date, 10).test()
        verify(mockCachedDataStore, times(10)).getApod(anyString())
        verify(mockRemoteDataStore, times(10)).getApod(anyString())
        verifyNoMoreInteractions(mockCachedDataStore)
        verifyNoMoreInteractions(mockRemoteDataStore)
        val expectedResponse = mutableListOf<APOD>()
        for(i in 1..10) {
            expectedResponse.add(stubSuccessCacheResponse)
        }
        repositoryTestObserver.assertValue(
            expectedResponse
        )
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
            .thenReturn(Maybe.just(stubSuccessCacheResponse))
    }

    fun stubCachedDataStoreError() {
        whenever(mockCachedDataStore.getApod(anyString()))
            .thenReturn(Maybe.empty())
    }

    fun stubRemoteDataStoreSuccess() {
        whenever(mockRemoteDataStore.getApod(anyString()))
            .thenReturn(Maybe.just(stubSuccessRemoteResponse))
    }

    fun stubRemoteDataStoreError() {
        whenever(mockRemoteDataStore.getApod(anyString()))
            .thenReturn(Maybe.empty())
    }
}
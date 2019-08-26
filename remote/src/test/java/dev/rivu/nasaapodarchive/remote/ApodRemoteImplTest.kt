package dev.rivu.nasaapodarchive.remote

import com.nhaarman.mockito_kotlin.*
import dev.rivu.nasaapodarchive.data.repository.ApodRemote
import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.remote.factory.APODFactory
import dev.rivu.nasaapodarchive.remote.mapper.ApodMapper
import dev.rivu.nasaapodarchive.remote.model.ApodResponse
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.ArgumentMatchers.anyString

class ApodRemoteImplTest {

    private lateinit var mockApodService: ApodService
    private lateinit var mockApodMapper: ApodMapper

    private lateinit var fakeApodResponse: ApodResponse
    private lateinit var fakeApod: APOD

    private lateinit var apodRemote: ApodRemote

    @Before
    fun setUp() {
        fakeApodResponse = APODFactory.makeApodResponse()
        fakeApod = APODFactory.makeApod()
        mockApodService = mock()
        mockApodMapper = mock {
            on { mapToDomain(type = fakeApodResponse) } doReturn fakeApod
        }

        apodRemote = ApodRemoteImpl(mockApodService, mockApodMapper)
    }

    @Test
    fun `getApod should return data and complete, if ApodService returns data`() {
        whenever(mockApodService.getApod(anyString()))
            .thenReturn(Single.just(fakeApodResponse))

        val testObserver = apodRemote.getApod("").test()
        testObserver.assertValue(fakeApod)
        testObserver.assertComplete()

        verify(mockApodService, times(1)).getApod(anyString())
        verify(mockApodMapper, times(1)).mapToDomain(any())
        verifyNoMoreInteractions(mockApodService)
        verifyNoMoreInteractions(mockApodMapper)
    }

    @Test
    fun `getApod should call onError if ApodService returns error`() {
        val exception = Exception()
        whenever(mockApodService.getApod(anyString()))
            .thenReturn(Single.error(exception))

        val testObserver = apodRemote.getApod("").test()
        testObserver.assertError(exception)
    }
}
package dev.rivu.nasaapodarchive.cache

import com.nhaarman.mockito_kotlin.*
import dev.rivu.nasaapodarchive.cache.dao.ApodDao
import dev.rivu.nasaapodarchive.cache.mapper.ApodEntityMapper
import dev.rivu.nasaapodarchive.cache.test.factory.APODFactory
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString

class ApodCacheImplTest {

    private lateinit var mockApodDao: ApodDao
    private lateinit var mockApodEntityMapper: ApodEntityMapper
    private lateinit var apodCache: ApodCacheImpl

    @Before
    fun setUp() {
        mockApodDao = mock()
        mockApodEntityMapper = mock()
        apodCache = ApodCacheImpl(mockApodDao, mockApodEntityMapper)
    }

    @Test
    fun clearApods() {
        whenever(mockApodDao.deleteAllApods())
            .thenReturn(Completable.complete())

        val testOvserver = apodCache.clearApods().test()
        testOvserver.assertComplete()

        verify(mockApodDao, times(1)).deleteAllApods()
        verifyNoMoreInteractions(mockApodDao)
        verifyNoMoreInteractions(mockApodEntityMapper)
    }

    @Test
    fun saveApods() {
        val fakeApodsList = APODFactory.makeApods(10)

        whenever(mockApodDao.saveApods(anyList()))
            .thenReturn(Completable.complete())

        val testOvserver = apodCache.saveApods(fakeApodsList).test()
        testOvserver.assertComplete()

        verify(mockApodDao, times(1)).saveApods(anyList())
        verify(mockApodEntityMapper, times(10)).mapFromDomain(any())
        verifyNoMoreInteractions(mockApodDao)
        verifyNoMoreInteractions(mockApodEntityMapper)
    }

    @Test
    fun getApod() {
        whenever(mockApodDao.getApod(anyString()))
            .thenReturn(Single.just(APODFactory.makeApodEntity()))
        whenever(mockApodEntityMapper.mapToDomain(any()))
            .thenReturn(APODFactory.makeApod())

        val testOvserver = apodCache.getApod(anyString()).test()
        testOvserver.assertComplete()

        verify(mockApodDao, times(1)).getApod(anyString())
        verify(mockApodEntityMapper, times(1)).mapToDomain(any())
        verifyNoMoreInteractions(mockApodDao)
        verifyNoMoreInteractions(mockApodEntityMapper)
    }

    @Test
    fun isCached() {
    }
}
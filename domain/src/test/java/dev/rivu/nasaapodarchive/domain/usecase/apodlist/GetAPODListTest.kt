package dev.rivu.nasaapodarchive.domain.usecase.apodlist

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import dev.rivu.nasaapodarchive.domain.factory.APODFactory
import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.domain.repository.APODListRepository
import dev.rivu.nasaapodarchive.domain.schedulers.FakeSchedulerProvider
import dev.rivu.nasaapodarchive.domain.schedulers.SchedulerProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString

class GetAPODListTest {
    private lateinit var getAPODList: GetAPODList
    private lateinit var schedulerProvider: SchedulerProvider
    private lateinit var mockAPODListRepository: APODListRepository

    private lateinit var usecaseParam: GetAPODList.Params

    @Before
    fun setup() {
        usecaseParam = GetAPODList.Params(
            startDate = "2018-08-25",
            count = 10
        )
        mockAPODListRepository = mock()
        schedulerProvider = FakeSchedulerProvider()
        getAPODList = GetAPODList(mockAPODListRepository, schedulerProvider)
    }

    @Test
    fun `execute should call APODListRepository#getApods`() {
        stubAPODListRepositoryGetApods(Single.just(APODFactory.makeAPODs()))
        getAPODList.execute(usecaseParam)
        verify(mockAPODListRepository, times(1)).getApods(usecaseParam.startDate, usecaseParam.count)
    }

    @Test
    fun `execute Flowable should complete`() {
        stubAPODListRepositoryGetApods(Single.just(APODFactory.makeAPODs()))
        val testSubscriber = getAPODList.execute(usecaseParam).test()
        testSubscriber.assertComplete()
    }

    @Test
    fun `execute returns proper data`() {
        val apodList = APODFactory.makeAPODs()
        stubAPODListRepositoryGetApods(Single.just(apodList))
        val testSubscriber = getAPODList.execute(usecaseParam).test()
        testSubscriber.assertValue(apodList)
    }

    private fun stubAPODListRepositoryGetApods(single: Single<List<APOD>>) {
        whenever(
            mockAPODListRepository.getApods(
                startDate = anyString(),
                count = anyInt()
            )
        )
            .thenReturn(single)
    }
}
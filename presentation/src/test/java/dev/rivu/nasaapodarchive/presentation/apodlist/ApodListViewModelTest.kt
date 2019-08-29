package dev.rivu.nasaapodarchive.presentation.apodlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.*
import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.domain.usecase.apodlist.GetAPODList
import dev.rivu.nasaapodarchive.presentation.apodlist.mapper.ApodViewMapper
import dev.rivu.nasaapodarchive.presentation.apodlist.model.ApodViewData
import dev.rivu.nasaapodarchive.presentation.test.factory.APODFactory
import dev.rivu.nasaapodarchive.presentation.test.factory.DataFactory
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Captor

class ApodListViewModelTest {
    private lateinit var actionProcessor: ApodListProcessor
    private lateinit var mockApodViewMapper: ApodViewMapper
    private lateinit var viewModel: ApodListViewModel
    private lateinit var mockGetAPODList: GetAPODList
    private lateinit var mockStateObserver: Observer<ApodListState>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Captor
    private lateinit var captor: KArgumentCaptor<ApodListState>

    @Before
    fun setup() {
        mockGetAPODList = mock()
        mockApodViewMapper = mock()
        actionProcessor = ApodListProcessor(mockGetAPODList)
        viewModel = ApodListViewModel(actionProcessor, mockApodViewMapper)
        mockStateObserver = mock()

        captor = argumentCaptor()

        viewModel.states().observeForever(mockStateObserver)
    }

    @Test
    fun `test initial intent`() {
        val list = APODFactory.makeApods(2)
        val viewList = APODFactory.makeApodViews(2)

        stubMapper(viewList[0], list[0])
        stubMapper(viewList[1], list[1])
        stubGetAPODList(Flowable.just(list))

        viewModel.processIntents(Observable.just(ApodListIntent.InitialIntent()))
        verify(mockStateObserver, times(1))
            .onChanged(
                ApodListState(//Loading State Update
                    isLoading = true,
                    isError = false,
                    errorMessage = ""
                )
            )
        verify(mockStateObserver, times(1))
            .onChanged(
                ApodListState(//Success State Update
                    isLoading = false,
                    isError = false,
                    errorMessage = "",
                    apodList = viewList,
                    detailDate = ""
                )
            )
    }

    @Test
    fun `test Load intent`() {
        val list = APODFactory.makeApods(2)
        val viewList = APODFactory.makeApodViews(2)

        stubMapper(viewList[0], list[0])
        stubMapper(viewList[1], list[1])
        stubGetAPODList(Flowable.just(list))

        viewModel.processIntents(Observable.just(ApodListIntent.LoadIntent(anyString(), anyInt())))
        verify(mockStateObserver, times(1))
            .onChanged(
                ApodListState(//Loading State Update
                    isLoading = true,
                    isError = false,
                    errorMessage = ""
                )
            )
        verify(mockStateObserver, times(1))
            .onChanged(
                ApodListState(//Success State Update
                    isLoading = false,
                    isError = false,
                    errorMessage = "",
                    apodList = viewList,
                    detailDate = ""
                )
            )
    }

    @Test
    fun `test Refresh intent`() {
        val list = APODFactory.makeApods(2)
        val viewList = APODFactory.makeApodViews(2)

        stubMapper(viewList[0], list[0])
        stubMapper(viewList[1], list[1])
        stubGetAPODList(Flowable.just(list))

        viewModel.processIntents(Observable.just(ApodListIntent.RefreshIntent(anyString(), anyInt())))
        verify(mockStateObserver, times(1))
            .onChanged(
                ApodListState(//Loading State Update
                    isLoading = true,
                    isError = false,
                    errorMessage = ""
                )
            )
        verify(mockStateObserver, times(1))
            .onChanged(
                ApodListState(//Success State Update
                    isLoading = false,
                    isError = false,
                    errorMessage = "",
                    apodList = viewList,
                    detailDate = ""
                )
            )
    }

    @Test
    fun `test Load More intent`() {
        val list = APODFactory.makeApods(2)
        val viewList = APODFactory.makeApodViews(2)

        stubMapper(viewList[0], list[0])
        stubMapper(viewList[1], list[1])
        stubGetAPODList(Flowable.just(list))

        viewModel.processIntents(Observable.just(ApodListIntent.LoadMoreIntent(anyString(), anyInt())))
        verify(mockStateObserver, times(1))
            .onChanged(
                ApodListState(//Loading State Update
                    isLoadingMore = true,
                    isError = false,
                    errorMessage = ""
                )
            )
        verify(mockStateObserver, times(1))
            .onChanged(
                ApodListState(//Success State Update
                    isLoadingMore = false,
                    isError = false,
                    errorMessage = "",
                    apodList = viewList,
                    detailDate = ""
                )
            )
    }

    @Test
    fun `test error state propagates when usecase returns error`() {
        val list = APODFactory.makeApods(2)
        val viewList = APODFactory.makeApodViews(2)

        val errorMessage = "Test Error"

        stubMapper(viewList[0], list[0])
        stubMapper(viewList[1], list[1])
        stubGetAPODList(Flowable.error(Exception(errorMessage)))

        viewModel.processIntents(Observable.just(ApodListIntent.LoadIntent(anyString(), anyInt())))
        verify(mockStateObserver, times(1))
            .onChanged(
                ApodListState(//Loading State Update
                    isLoading = false,
                    isError = true,
                    errorMessage = errorMessage
                )
            )
    }

    @Test
    fun `test Click intent`() {
        val list = APODFactory.makeApods(2)
        val viewList = APODFactory.makeApodViews(2)

        stubMapper(viewList[0], list[0])
        stubMapper(viewList[1], list[1])
        stubGetAPODList(Flowable.just(list))

        val date = DataFactory.randomString()

        viewModel.processIntents(Observable.just(ApodListIntent.ClickIntent(date)))
        verify(mockStateObserver, times(1))
            .onChanged(
                ApodListState(//Loading State Update
                    isLoading = false,
                    isError = false,
                    errorMessage = "",
                    detailDate = date
                )
            )
    }

    private fun stubMapper(apodView: ApodViewData,
                                            apod: APOD) {
        whenever(mockApodViewMapper.mapFromDomain(apod))
            .thenReturn(apodView)
        whenever(mockApodViewMapper.mapToDomain(apodView))
            .thenReturn(apod)
    }

    private fun stubGetAPODList(flowable: Flowable<List<APOD>>) {
        whenever(mockGetAPODList.execute(anyOrNull(), any()))
            .thenReturn(flowable)
    }
}
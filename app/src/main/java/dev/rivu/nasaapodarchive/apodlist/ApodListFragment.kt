package dev.rivu.nasaapodarchive.apodlist

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.jakewharton.rxbinding3.view.detaches
import dev.rivu.nasaapodarchive.R
import dev.rivu.nasaapodarchive.base.BaseFragment
import dev.rivu.nasaapodarchive.domain.utils.format
import dev.rivu.nasaapodarchive.presentation.apodlist.ApdListViewModelFactory
import dev.rivu.nasaapodarchive.presentation.apodlist.ApodListIntent
import dev.rivu.nasaapodarchive.presentation.apodlist.ApodListState
import dev.rivu.nasaapodarchive.presentation.apodlist.ApodListViewModel
import dev.rivu.nasaapodarchive.presentation.apodlist.model.ApodViewData
import dev.rivu.nasaapodarchive.presentation.base.MviView
import dev.rivu.nasaapodarchive.utils.get
import dev.rivu.nasaapodarchive.utils.gone
import dev.rivu.nasaapodarchive.utils.isVisible
import dev.rivu.nasaapodarchive.utils.visible
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_apodlist.*
import java.util.*
import javax.inject.Inject

class ApodListFragment : BaseFragment(), MviView<ApodListIntent, ApodListState> {

    var showApodImageDetail: (view: View, apodViewData: ApodViewData) -> Unit = { _, _ -> }

    @Inject
    lateinit var viewModelFactory: ApdListViewModelFactory

    private val clearClickPublisher: PublishSubject<ApodListIntent.ClearClickIntent> by lazy {
        PublishSubject.create<ApodListIntent.ClearClickIntent>()
    }


    private val adapter by lazy {
        ApodListAdapter()
    }

    private lateinit var layoutManager: GridLayoutManager

    var today: String = Calendar.getInstance().time.format()

    private val apodListViewModel: ApodListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(ApodListViewModel::class.java)
    }

    override fun layoutId(): Int = R.layout.fragment_apodlist

    override fun initView() {
        layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        rvApodlist.layoutManager = layoutManager
        rvApodlist.adapter = adapter
    }

    private fun showImageDetailsAndClear(view: View, apodViewData: ApodViewData) {
        showApodImageDetail(view, apodViewData)
        clearClickPublisher.onNext(ApodListIntent.ClearClickIntent)
    }

    override fun bind() {
        apodListViewModel.states()
            .observe(this, Observer<ApodListState> { state ->
                render(state)
            })
        apodListViewModel.processIntents(intents())
    }

    override fun intents(): Observable<ApodListIntent> {
        return Observable.merge(
            Observable.just(ApodListIntent.InitialIntent(today, 10)),
            swipeRefreshApodlist.refreshes().map {
                ApodListIntent.RefreshIntent(today, 10)
            },
            adapter.clickEvent
                .map { clickData ->
                    ApodListIntent.ClickIntent(
                        clickedViewPosition = clickData.position,
                        date = clickData.apodViewData.date.format()
                    )
                }
                .takeUntil(rvApodlist.detaches()),
            clearClickPublisher
        )
    }

    private fun showLoading() {
        hideErrorView()
        if (swipeRefreshApodlist.isVisible()) {
            swipeRefreshApodlist.isRefreshing = true
        } else {
            progress.visible()
        }

    }

    private fun hideLoading() {
        if (swipeRefreshApodlist.isVisible()) {
            swipeRefreshApodlist.isRefreshing = false
        } else {
            progress.gone()
            swipeRefreshApodlist.visible()
        }

    }

    private fun showError(errorMessage: String) {
        errorView.visible()
        errorView.setErrorMessage(errorMessage)
    }

    private fun hideErrorView() {
        if (errorView.isVisible()) {
            errorView.gone()
        }
    }

    override fun render(state: ApodListState) {
        if (state.isLoading) {
            showLoading()
            return
        } else {
            hideLoading()
        }
        if (state.isError) {
            showError(state.errorMessage)
            return
        } else {
            hideErrorView()
        }
        if (state.isLoadingMore) {
            adapter.showLoadingMore()
        } else {
            adapter.hideLoadingMore()
        }
        if (state.apodList.isNotEmpty()) {
            adapter.updateItems(apodItemList = state.apodList)
        }
        if (!state.detailDate.isBlank() && state.clickedViewPosition in state.apodList.indices) {
            showImageDetailsAndClear(
                layoutManager.findViewByPosition(state.clickedViewPosition)!!,
                state.apodList[state.detailDate]!!
            )
        }
    }
}

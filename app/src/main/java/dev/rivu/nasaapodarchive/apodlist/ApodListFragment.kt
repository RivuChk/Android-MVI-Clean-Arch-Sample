package dev.rivu.nasaapodarchive.apodlist

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private val refreshPublisher: PublishSubject<ApodListIntent.RefreshIntent> = PublishSubject.create()
    private val clickPublisher: PublishSubject<ApodListIntent.ClickIntent> = PublishSubject.create()
    private val clearClickPublisher: PublishSubject<ApodListIntent.ClearClickIntent> = PublishSubject.create()

    private val adapter by lazy {
        ApodListAdapter(::onApodClick)
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
        swipeRefreshApodlist.setOnRefreshListener {
            refreshPublisher.onNext(ApodListIntent.RefreshIntent(today, 10))
        }
    }

    private fun onApodClick(clickedViewPosition: Int, apodViewData: ApodViewData) {
        clickPublisher.onNext(
            ApodListIntent.ClickIntent(
                clickedViewPosition = clickedViewPosition,
                date = apodViewData.date.format()
            )
        )
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
            refreshPublisher.hide(),
            clickPublisher.hide(),
            clearClickPublisher.hide()
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
        if (!state.detailDate.isBlank()) {
            showImageDetailsAndClear(
                layoutManager.findViewByPosition(state.clickedViewPosition)!!,
                state.apodList[state.detailDate]!!
            )
        }
    }
}

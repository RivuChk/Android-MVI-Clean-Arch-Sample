package dev.rivu.nasaapodarchive.apodlist

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.rivu.nasaapodarchive.R
import dev.rivu.nasaapodarchive.base.BaseFragment
import dev.rivu.nasaapodarchive.presentation.apodlist.ApdListViewModelFactory
import dev.rivu.nasaapodarchive.presentation.apodlist.ApodListIntent
import dev.rivu.nasaapodarchive.presentation.apodlist.ApodListState
import dev.rivu.nasaapodarchive.presentation.apodlist.ApodListViewModel
import dev.rivu.nasaapodarchive.presentation.base.MviView
import dev.rivu.nasaapodarchive.domain.utils.format
import dev.rivu.nasaapodarchive.utils.gone
import dev.rivu.nasaapodarchive.utils.isVisible
import dev.rivu.nasaapodarchive.utils.visible
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_apodlist.*
import java.util.*
import javax.inject.Inject

class ApodListFragment : BaseFragment(), MviView<ApodListIntent, ApodListState> {

    @Inject
    lateinit var viewModelFactory: ApdListViewModelFactory

    private val refreshPublisher: PublishSubject<ApodListIntent.RefreshIntent> = PublishSubject.create()

    val adapter by lazy {
        ApodListAdapter()
    }

    var today: String = Calendar.getInstance().time.format()

    private val apodListViewModel: ApodListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(ApodListViewModel::class.java)
    }

    override fun layoutId(): Int = R.layout.fragment_apodlist

    override fun initView() {
        rvApodlist.layoutManager = GridLayoutManager(context,2, StaggeredGridLayoutManager.VERTICAL, false)
        rvApodlist.adapter = adapter
        swipeRefreshApodlist.setOnRefreshListener {
            refreshPublisher.onNext(ApodListIntent.RefreshIntent(today, 10))
        }
    }

    override fun bind() {

        apodListViewModel.states()
            .observe(this, Observer<ApodListState> { state ->
                render(state)
            })
        apodListViewModel.processIntents(intents())
    }

    override fun intents(): Observable<ApodListIntent> {
        return Observable.merge(Observable.just(ApodListIntent.InitialIntent(today, 10)), refreshPublisher.hide())
    }

    private fun showLoading() {
        hideErrorView()
        if(swipeRefreshApodlist.isVisible()) {
            swipeRefreshApodlist.isRefreshing = true
        } else {
            progress.visible()
        }

    }

    private fun hideLoading() {
        if(swipeRefreshApodlist.isVisible()) {
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
        if(errorView.isVisible()) {
            errorView.gone()
        }
    }

    override fun render(state: ApodListState) {
        if(state.isLoading) {
            showLoading()
        } else {
            hideLoading()
        }
        if(state.isError) {
            showError(state.errorMessage)
        } else {
            hideErrorView()
        }
        if(state.isLoadingMore) {
            adapter.showLoadingMore()
        } else {
            adapter.hideLoadingMore()
        }
        if(state.apodList.isNotEmpty()) {
            adapter.updateItems(apodItemList = state.apodList)
        }
        if(!state.detailDate.isNullOrBlank()) {
            //TODO: Detail Screen
            Toast.makeText(context, state.detailDate, Toast.LENGTH_LONG).show()
        }
    }
}

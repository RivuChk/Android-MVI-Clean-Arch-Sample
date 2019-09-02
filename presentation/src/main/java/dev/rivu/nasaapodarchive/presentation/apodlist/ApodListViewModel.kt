package dev.rivu.nasaapodarchive.presentation.apodlist

import dev.rivu.nasaapodarchive.presentation.apodlist.mapper.ApodViewMapper
import dev.rivu.nasaapodarchive.presentation.base.BaseViewModel
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ApodListViewModel @Inject constructor(
    override val actionProcessor: ApodListProcessor,
    private val apodViewMapper: ApodViewMapper
) : BaseViewModel<ApodListIntent, ApodListState, ApodListAction, ApodListResult>() {

    override fun intentFilter(): FlowableTransformer<ApodListIntent, ApodListIntent> {
        return FlowableTransformer { intents ->
            intents.publish { shared ->
                Flowable.merge<ApodListIntent>(
                    shared.ofType(ApodListIntent.InitialIntent::class.java).take(1),
                    shared.filter { it !is ApodListIntent.InitialIntent }
                )
            }
        }
    }

    override fun actionFromIntent(intent: ApodListIntent): ApodListAction {
        return when (intent) {
            is ApodListIntent.LoadIntent, is ApodListIntent.InitialIntent, is ApodListIntent.RefreshIntent -> {
                ApodListAction.Load(intent.startDate, intent.count)
            }
            is ApodListIntent.LoadMoreIntent -> {
                ApodListAction.LoadMore(intent.startDate, intent.count)
            }
            is ApodListIntent.ClickIntent -> {
                ApodListAction.Click(intent.clickedViewPosition, intent.date)
            }
            is ApodListIntent.ClearClickIntent -> {
                ApodListAction.ClearClick
            }
        }
    }

    override fun reducer(): BiFunction<ApodListState, ApodListResult, ApodListState> =
        BiFunction { previousState, result ->
            when(result) {
                is ApodListResult.LoadResult.Success -> {
                    previousState.copy(
                        isLoading = false,
                        isError = false,
                        errorMessage = "",
                        apodList = result.apodList.map(apodViewMapper::mapFromDomain),
                        detailDate = ""
                    )
                }
                is ApodListResult.LoadResult.Failure -> {
                    previousState.copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = result.errorMessage
                    )
                }
                is ApodListResult.LoadResult.InProgress -> {
                    previousState.copy(
                        isLoading = true,
                        isError = false,
                        errorMessage = ""
                    )
                }
                is ApodListResult.LoadMoreResult.Success -> {
                    previousState.copy(
                        isLoadingMore = false,
                        isError = false,
                        errorMessage = "",
                        apodList = previousState.apodList + result.apodList.map(apodViewMapper::mapFromDomain),
                        detailDate = ""
                    )
                }
                is ApodListResult.LoadMoreResult.Failure -> {
                    previousState.copy(
                        isLoadingMore = false,
                        isError = true,
                        errorMessage = result.errorMessage
                    )
                }
                is ApodListResult.LoadMoreResult.InProgress -> {
                    previousState.copy(
                        isError = false,
                        isLoadingMore = true,
                        errorMessage = ""
                    )
                }
                is ApodListResult.ClickResult -> {
                    previousState.copy(
                        detailDate = result.date
                    )
                }
                is ApodListResult.ClearResult -> {
                    previousState.copy(
                        detailDate = "",
                        clickedViewPosition = 0
                    )
                }
            }
        }

    override fun initialState(): ApodListState = ApodListState.idle()

}
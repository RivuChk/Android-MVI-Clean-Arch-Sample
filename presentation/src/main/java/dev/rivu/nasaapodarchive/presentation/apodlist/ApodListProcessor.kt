package dev.rivu.nasaapodarchive.presentation.apodlist

import dev.rivu.nasaapodarchive.domain.usecase.apodlist.GetAPODList
import dev.rivu.nasaapodarchive.presentation.base.MviActionProcessor
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import javax.inject.Inject


open class ApodListProcessor @Inject constructor(private val usecase: GetAPODList) :
    MviActionProcessor<ApodListAction, ApodListResult> {

    override fun transformFromAction(): FlowableTransformer<ApodListAction, ApodListResult> =
        FlowableTransformer { actionObservable ->
            actionObservable.publish { shared ->
                Flowable.merge(
                    shared.ofType(ApodListAction.Load::class.java).compose(loadApods()),
                    shared.ofType(ApodListAction.LoadMore::class.java).compose(loadMoreApods()),
                    shared.ofType(ApodListAction.Click::class.java).compose(click())
                )
            }
        }

    private fun loadApods(): FlowableTransformer<ApodListAction.Load, ApodListResult.LoadResult> =
        FlowableTransformer { action ->
            action.switchMap { apodAction ->
                usecase.execute(GetAPODList.Params(apodAction.startDate, apodAction.count))
                    .map { apodList ->
                        ApodListResult.LoadResult.Success(apodList)
                    }
                    .cast(ApodListResult.LoadResult::class.java)
                    .onErrorReturn { error ->
                        ApodListResult.LoadResult.Failure(error.localizedMessage)
                    }
                    .startWith(ApodListResult.LoadResult.InProgress)
            }
        }

    private fun loadMoreApods(): FlowableTransformer<ApodListAction.LoadMore, ApodListResult.LoadMoreResult> =
        FlowableTransformer { action ->
            action.switchMap { apodAction ->
                usecase.execute(GetAPODList.Params(apodAction.startDate, apodAction.count))
                    .map { apodList ->
                        ApodListResult.LoadMoreResult.Success(apodList)
                    }
                    .cast(ApodListResult.LoadMoreResult::class.java)
                    .onErrorReturn { error ->
                        ApodListResult.LoadMoreResult.Failure(error.localizedMessage)
                    }
                    .startWith(ApodListResult.LoadMoreResult.InProgress)
            }
        }

    private fun click(): FlowableTransformer<ApodListAction.Click, ApodListResult.ClickResult> =
        FlowableTransformer { action ->
            action.flatMap { clickAction ->
                Flowable.just(ApodListResult.ClickResult(clickAction.date))
            }
        }
}
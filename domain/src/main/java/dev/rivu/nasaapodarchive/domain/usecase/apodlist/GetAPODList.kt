package dev.rivu.nasaapodarchive.domain.usecase.apodlist

import dev.rivu.nasaapodarchive.domain.usecase.BaseFlowableUseCase
import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.domain.repository.APODListRepository
import dev.rivu.nasaapodarchive.domain.schedulers.SchedulerProvider
import io.reactivex.Flowable
import javax.inject.Inject

open class GetAPODList @Inject constructor(
    private val apodListRepository: APODListRepository,
    schedulerProvider: SchedulerProvider
) : BaseFlowableUseCase<List<APOD>, GetAPODList.Params>(schedulerProvider) {

    override fun buildUseCase(param: Params): Flowable<List<APOD>> =
        apodListRepository.getApods(param.startDate, param.count)
            .toFlowable()

    data class Params(
        val startDate: String,
        val count: Int = 10
    )
}
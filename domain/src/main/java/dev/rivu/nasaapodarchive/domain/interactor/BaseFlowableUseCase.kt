package dev.rivu.nasaapodarchive.domain.interactor

import dev.rivu.nasaapodarchive.domain.schedulers.SchedulerProvider
import io.reactivex.Flowable

abstract class BaseFlowableUseCase<Data, in Param> constructor(val schedulerProvider: SchedulerProvider) {

    protected abstract fun buildUseCase(param: Param): Flowable<Data>

    open fun execute(param: Param, executionType: ExecutionType = ExecutionType.IO): Flowable<Data> {
        return buildUseCase(param)
            .subscribeOn(
                if(executionType is ExecutionType.IO) {
                    schedulerProvider.io()
                } else {
                    schedulerProvider.computation()
                }
            )
            .observeOn(schedulerProvider.ui())
    }

    sealed class ExecutionType {
        object Computation: ExecutionType()
        object IO: ExecutionType()
    }
}
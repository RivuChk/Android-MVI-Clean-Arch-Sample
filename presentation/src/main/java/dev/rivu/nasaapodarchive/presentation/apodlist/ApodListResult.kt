package dev.rivu.nasaapodarchive.presentation.apodlist

import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.presentation.base.MviResult

sealed class ApodListResult: MviResult {
    sealed class LoadResult : ApodListResult() {
        data class Success(val apodList: List<APOD>): LoadResult()
        data class Failure(val errorMessage: String): LoadResult()
        object InProgress: LoadResult()
    }
    sealed class LoadMoreResult : ApodListResult() {
        data class Success(val apodList: List<APOD>): LoadMoreResult()
        data class Failure(val errorMessage: String): LoadMoreResult()
        object InProgress: LoadMoreResult()
    }
    data class ClickResult(val date: String): ApodListResult()

    object ClearResult: ApodListResult()
}
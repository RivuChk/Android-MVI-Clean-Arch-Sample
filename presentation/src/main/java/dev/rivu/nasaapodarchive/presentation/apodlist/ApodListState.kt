package dev.rivu.nasaapodarchive.presentation.apodlist

import android.view.View
import dev.rivu.nasaapodarchive.presentation.apodlist.model.ApodViewData
import dev.rivu.nasaapodarchive.presentation.base.MviState

data class ApodListState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val apodList: List<ApodViewData> = emptyList(),
    val isError: Boolean = false,
    val errorMessage: String = "",
    val detailDate: String = "",
    val clickedViewPosition: Int = -1
): MviState {
    companion object {
        @JvmStatic
        fun idle() = ApodListState()
    }
}
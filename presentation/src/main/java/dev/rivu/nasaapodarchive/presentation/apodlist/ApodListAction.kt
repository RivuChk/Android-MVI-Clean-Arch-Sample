package dev.rivu.nasaapodarchive.presentation.apodlist

import dev.rivu.nasaapodarchive.presentation.base.MviAction

sealed class ApodListAction: MviAction {
    data class Load(val startDate: String, val count: Int) :
        ApodListAction()//Should be used for all 3, Load, Reload

    data class LoadMore(val startDate: String, val count: Int) :
        ApodListAction()//Should be used for all 3, Load, Reload

    data class Click(val date: String): ApodListAction()
}
package dev.rivu.nasaapodarchive.presentation.apodlist

import dev.rivu.nasaapodarchive.presentation.base.MviIntent
import dev.rivu.nasaapodarchive.domain.utils.format
import java.util.*

sealed class ApodListIntent(val startDate: String, val count: Int) : MviIntent {
    class InitialIntent(startDate: String = Calendar.getInstance().time.format(), count: Int = 10) : ApodListIntent(startDate, count)
    class LoadIntent(startDate: String, count: Int) : ApodListIntent(startDate, count)
    class RefreshIntent(startDate: String, count: Int) : ApodListIntent(startDate, count)
    class LoadMoreIntent(startDate: String, count: Int) : ApodListIntent(startDate, count)
    data class ClickIntent(val date: String) : ApodListIntent("", 0)
}
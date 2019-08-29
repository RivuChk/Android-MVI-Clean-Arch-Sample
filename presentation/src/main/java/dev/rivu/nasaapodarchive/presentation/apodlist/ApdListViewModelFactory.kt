package dev.rivu.nasaapodarchive.presentation.apodlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rivu.nasaapodarchive.presentation.apodlist.mapper.ApodViewMapper
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class ApdListViewModelFactory @Inject constructor(
    private val actionProcessor: ApodListProcessor,
    private val apodViewMapper: ApodViewMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass == ApodListViewModel::class.java) {
            return ApodListViewModel(actionProcessor, apodViewMapper) as T
        } else {
            throw UnsupportedOperationException("ApdListViewModelFactory can only produce ApodListViewModel")
        }
    }
}
package dev.rivu.nasaapodarchive.presentation.apodlist.injection

import dagger.Module
import dagger.Provides
import dev.rivu.nasaapodarchive.domain.usecase.apodlist.GetAPODList
import dev.rivu.nasaapodarchive.presentation.apodlist.ApdListViewModelFactory
import dev.rivu.nasaapodarchive.presentation.apodlist.ApodListProcessor
import dev.rivu.nasaapodarchive.presentation.apodlist.mapper.ApodViewMapper
import javax.inject.Singleton

@Module
class PresentationModule {

    @Provides
    @Singleton
    fun provideApodChachedDataStore(): ApodViewMapper =
        ApodViewMapper()

    @Provides
    @Singleton
    fun provideApodRemoteDataStore(getAPODList: GetAPODList): ApodListProcessor =
        ApodListProcessor(getAPODList)

    @Provides
    @Singleton
    fun provideApdListViewModelFactory(apodListProcessor: ApodListProcessor, apodViewMapper: ApodViewMapper): ApdListViewModelFactory =
        ApdListViewModelFactory(apodListProcessor, apodViewMapper)

}
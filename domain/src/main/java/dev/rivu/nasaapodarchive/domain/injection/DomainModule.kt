package dev.rivu.nasaapodarchive.domain.injection

import dagger.Module
import dagger.Provides
import dev.rivu.nasaapodarchive.domain.repository.APODListRepository
import dev.rivu.nasaapodarchive.domain.schedulers.SchedulerProvider
import dev.rivu.nasaapodarchive.domain.schedulers.SchedulerProviderImpl
import dev.rivu.nasaapodarchive.domain.usecase.apodlist.GetAPODList
import javax.inject.Singleton

@Module
class DomainModule {


    @Provides
    @Singleton
    fun provideScheduler(): SchedulerProvider = SchedulerProviderImpl()

    @Provides
    @Singleton
    fun provideGetAPODList(apodListRepository: APODListRepository, schedulerProvider: SchedulerProvider): GetAPODList =
        GetAPODList(apodListRepository, schedulerProvider)

}
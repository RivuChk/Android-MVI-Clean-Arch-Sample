package dev.rivu.nasaapodarchive.data.injection

import dagger.Module
import dagger.Provides
import dev.rivu.nasaapodarchive.data.APODListRepositoryImpl
import dev.rivu.nasaapodarchive.data.repository.ApodCache
import dev.rivu.nasaapodarchive.data.repository.ApodDataStore
import dev.rivu.nasaapodarchive.data.repository.ApodRemote
import dev.rivu.nasaapodarchive.data.source.ApodCachedDataStore
import dev.rivu.nasaapodarchive.data.source.ApodRemoteDataStore
import dev.rivu.nasaapodarchive.domain.repository.APODListRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    @Named("cache")
    fun provideApodChachedDataStore(apodCache: ApodCache): ApodDataStore =
        ApodCachedDataStore(apodCache)

    @Provides
    @Singleton
    @Named("remote")
    fun provideApodRemoteDataStore(apodRemote: ApodRemote): ApodDataStore =
        ApodRemoteDataStore(apodRemote)

    @Provides
    @Singleton
    fun provideAPODListRepository(@Named("cache") cachedDataStore: ApodDataStore, @Named("remote") remoteDataStore: ApodDataStore): APODListRepository =
        APODListRepositoryImpl(cachedDataStore, remoteDataStore)

}
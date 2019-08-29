package dev.rivu.nasaapodarchive.remote.injection

import dagger.Module
import dagger.Provides
import dev.rivu.nasaapodarchive.data.repository.ApodRemote
import dev.rivu.nasaapodarchive.remote.ApodRemoteImpl
import dev.rivu.nasaapodarchive.remote.ApodService
import dev.rivu.nasaapodarchive.remote.ApodServiceFactory
import dev.rivu.nasaapodarchive.remote.BuildConfig
import dev.rivu.nasaapodarchive.remote.mapper.ApodMapper
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideApodService(): ApodService =
        ApodServiceFactory.makeApodService(BuildConfig.DEBUG)

    @Provides
    @Singleton
    fun provideApodMapper(): ApodMapper = ApodMapper()

    @Provides
    @Singleton
    fun providesApodRemote(apodService: ApodService, apodMapper: ApodMapper): ApodRemote =
        ApodRemoteImpl(apodService, apodMapper)
}
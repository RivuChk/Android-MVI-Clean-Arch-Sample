package dev.rivu.nasaapodarchive

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.rivu.nasaapodarchive.cache.injection.CacheModule
import dev.rivu.nasaapodarchive.data.injection.DataModule
import dev.rivu.nasaapodarchive.domain.injection.DomainModule
import dev.rivu.nasaapodarchive.injection.components.DaggerAppComponent
import dev.rivu.nasaapodarchive.presentation.apodlist.injection.PresentationModule
import dev.rivu.nasaapodarchive.remote.injection.RemoteModule
import org.buffer.injection.module.AppModule

class ApodApp: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .cacheModule(CacheModule(this))
            .presentationModule(PresentationModule())
            .dataModule(DataModule())
            .domainModule(DomainModule())
            .remoteModule(RemoteModule())
            .build()
    }
}
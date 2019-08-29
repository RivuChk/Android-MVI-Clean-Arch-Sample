package dev.rivu.nasaapodarchive.injection.components

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.rivu.nasaapodarchive.ApodApp
import dev.rivu.nasaapodarchive.cache.injection.CacheModule
import dev.rivu.nasaapodarchive.data.injection.DataModule
import dev.rivu.nasaapodarchive.domain.injection.DomainModule
import dev.rivu.nasaapodarchive.injection.modules.AndroidComponentsBindingModule
import dev.rivu.nasaapodarchive.presentation.apodlist.injection.PresentationModule
import dev.rivu.nasaapodarchive.remote.injection.RemoteModule
import org.buffer.injection.module.AppModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        CacheModule::class, DataModule::class,
        DomainModule::class, PresentationModule::class,
        RemoteModule::class, AndroidComponentsBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<ApodApp> {
    override fun inject(app: ApodApp)

}
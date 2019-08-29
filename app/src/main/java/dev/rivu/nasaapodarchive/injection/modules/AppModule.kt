package org.buffer.injection.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dev.rivu.nasaapodarchive.domain.schedulers.SchedulerProvider
import dev.rivu.nasaapodarchive.domain.schedulers.SchedulerProviderImpl
import dev.rivu.nasaapodarchive.domain.schedulers.SchedulerProviderImpl_Factory
import javax.inject.Singleton

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
class AppModule(val applicationContext: Context) {

    @Provides
    @Singleton
    fun bindContext(): Context = applicationContext

    @Provides
    @Singleton
    fun provideScheduler(): SchedulerProvider = SchedulerProviderImpl()
}
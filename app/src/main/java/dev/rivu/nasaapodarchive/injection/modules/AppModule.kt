package org.buffer.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
class AppModule(val applicationContext: Context) {

    @Provides
    @Singleton
    fun bindContext(): Context = applicationContext
}
package dev.rivu.nasaapodarchive.cache.injection

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.rivu.nasaapodarchive.cache.ApodCacheImpl
import dev.rivu.nasaapodarchive.cache.dao.ApodDao
import dev.rivu.nasaapodarchive.cache.db.ApodDatabase
import dev.rivu.nasaapodarchive.cache.mapper.ApodEntityMapper
import dev.rivu.nasaapodarchive.data.repository.ApodCache
import javax.inject.Singleton

@Module
class CacheModule(val applicationContext: Context) {

    @Provides
    @Singleton
    fun provideDB(): ApodDatabase =
        Room.databaseBuilder(applicationContext, ApodDatabase::class.java, "apod.db").build()

    @Provides
    @Singleton
    fun provideApodDao(db: ApodDatabase): ApodDao = db.apodDao()

    @Provides
    @Singleton
    fun providesMapper(): ApodEntityMapper = ApodEntityMapper()

    @Provides
    @Singleton
    fun providesApodCache(apodDao: ApodDao, apodEntityMapper: ApodEntityMapper): ApodCache =
        ApodCacheImpl(apodDao, apodEntityMapper)
}
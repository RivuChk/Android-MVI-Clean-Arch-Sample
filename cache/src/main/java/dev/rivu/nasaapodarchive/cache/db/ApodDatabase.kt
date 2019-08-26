package dev.rivu.nasaapodarchive.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.rivu.nasaapodarchive.cache.dao.ApodDao
import dev.rivu.nasaapodarchive.cache.model.ApodEntity
import javax.inject.Inject

@Database(entities = [ApodEntity::class], version = 1)
abstract class ApodDatabase: RoomDatabase() {
    abstract fun apodDao(): ApodDao
}
package dev.rivu.nasaapodarchive.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.rivu.nasaapodarchive.cache.model.ApodEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ApodDao {

    @Insert
    fun saveApods(apods: List<ApodEntity>): Completable

    @Query("select * from apod where date like :date")
    fun getApod(date: String): Single<ApodEntity>

    @Query("DELETE from apod")
    fun deleteAllApods(): Completable
}
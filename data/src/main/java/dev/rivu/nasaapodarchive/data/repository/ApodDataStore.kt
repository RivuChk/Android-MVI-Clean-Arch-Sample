package dev.rivu.nasaapodarchive.data.repository

import dev.rivu.nasaapodarchive.domain.model.APOD
import io.reactivex.Completable
import io.reactivex.Single

interface ApodDataStore {
    fun getApod(date: String): Single<APOD>

    fun saveApods(apods: List<APOD>): Completable

    fun clearApods(): Completable

    fun isCached(): Single<Boolean>
}
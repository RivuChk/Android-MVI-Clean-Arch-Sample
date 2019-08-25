package dev.rivu.nasaapodarchive.domain.repository

import dev.rivu.nasaapodarchive.domain.model.APOD
import io.reactivex.Completable
import io.reactivex.Single

interface APODListRepository {
    fun getApod(date: String): Single<APOD>
    fun getApods(startDate: String, count: Int): Single<List<APOD>>
    fun saveApods(vararg apods: APOD): Completable
    fun cleareApods(): Completable
}
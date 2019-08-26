package dev.rivu.nasaapodarchive.data.repository

import dev.rivu.nasaapodarchive.domain.model.APOD
import io.reactivex.Completable
import io.reactivex.Single

interface ApodCache {
    /**
     * Clear all Apods from the cache.
     */
    fun clearApods(): Completable

    /**
     * Save a given list of Apods to the cache.
     */
    fun saveApods(apods: List<APOD>): Completable


    /**
     * Retrieve a single Apod, from the cache.
     */
    fun getApod(date: String): Single<APOD>

    /**
     * Check whether there is a list of Apods stored in the cache.
     *
     * @return true if the list is cached, otherwise false
     */
    fun isCached(): Single<Boolean>
}
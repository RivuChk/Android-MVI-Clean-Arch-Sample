package dev.rivu.nasaapodarchive.data.source

import dev.rivu.nasaapodarchive.data.repository.ApodCache
import dev.rivu.nasaapodarchive.data.repository.ApodDataStore
import dev.rivu.nasaapodarchive.domain.model.APOD
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

open class ApodCachedDataStore @Inject constructor(private val apodCache: ApodCache): ApodDataStore {
    override fun getApod(date: String): Maybe<APOD> {
        return apodCache.getApod(date)
    }

    override fun saveApods(apods: List<APOD>): Completable {
        return apodCache.saveApods(apods)
    }

    override fun clearApods(): Completable {
        return apodCache.clearApods()
    }

    override fun isCached(): Single<Boolean> {
        return apodCache.isCached()
    }
}
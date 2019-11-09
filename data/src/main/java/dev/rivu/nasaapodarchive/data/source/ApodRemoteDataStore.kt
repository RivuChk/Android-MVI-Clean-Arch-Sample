package dev.rivu.nasaapodarchive.data.source

import dev.rivu.nasaapodarchive.data.repository.ApodDataStore
import dev.rivu.nasaapodarchive.data.repository.ApodRemote
import dev.rivu.nasaapodarchive.domain.model.APOD
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

open class ApodRemoteDataStore @Inject constructor(private val apodRemote: ApodRemote): ApodDataStore {
    override fun getApod(date: String): Maybe<APOD> {
        return apodRemote.getApod(date)
            .toMaybe()
            .onErrorComplete()
    }

    override fun saveApods(apods: List<APOD>): Completable {
        throw UnsupportedOperationException()
    }

    override fun clearApods(): Completable {
        throw UnsupportedOperationException()
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }
}
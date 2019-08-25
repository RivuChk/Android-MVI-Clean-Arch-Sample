package dev.rivu.nasaapodarchive.data

import dev.rivu.nasaapodarchive.data.repository.ApodDataStore
import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.domain.repository.APODListRepository
import dev.rivu.nasaapodarchive.utils.daysAgo
import dev.rivu.nasaapodarchive.utils.format
import dev.rivu.nasaapodarchive.utils.parseDate
import io.reactivex.*

class APODListRepositoryImpl(
    private val cachedDataStore: ApodDataStore,
    private val remoteDataStore: ApodDataStore
) : APODListRepository {
    override fun getApod(date: String): Single<APOD> {
        return cachedDataStore
            .getApod(date)
            .onErrorResumeNext {
                remoteDataStore.getApod(date)
                    .flatMap { apod ->
                        saveApods(apod)
                            .toSingle { apod }
                    }
            }
    }

    override fun getApods(startDate: String, count: Int): Single<List<APOD>> {
        return Flowable.create<String>({ emitter ->
            val start = startDate.parseDate()
            emitter.onNext(startDate)
            for (i in 1 until count) {
                val date = start.daysAgo(i).format()
                emitter.onNext(date)
            }
            emitter.onComplete()
        }, BackpressureStrategy.BUFFER).concatMap { date ->
            getApod(date)
                .toFlowable()
        }.toList()
    }

    override fun saveApods(vararg apods: APOD): Completable {
        return cachedDataStore.saveApods(apods.asList())
    }

    override fun cleareApods(): Completable {
        return cachedDataStore.clearApods()
    }
}
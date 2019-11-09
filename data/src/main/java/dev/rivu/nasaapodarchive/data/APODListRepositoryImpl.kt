package dev.rivu.nasaapodarchive.data

import dev.rivu.nasaapodarchive.data.repository.ApodDataStore
import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.domain.repository.APODListRepository
import dev.rivu.nasaapodarchive.domain.utils.daysAgo
import dev.rivu.nasaapodarchive.domain.utils.format
import dev.rivu.nasaapodarchive.domain.utils.parseDate
import io.reactivex.*
import javax.inject.Named

class APODListRepositoryImpl(
    @Named("cache") private val cachedDataStore: ApodDataStore,
    @Named("remote") private val remoteDataStore: ApodDataStore
) : APODListRepository {
    override fun getApod(date: String): Single<APOD> {
        return cachedDataStore
            .getApod(date)
            .switchIfEmpty(
                remoteDataStore.getApod(date)
                    .flatMap { apod ->
                        saveApods(apod)
                            .andThen(
                                Maybe.just(apod)
                            )
                    }
                    .toSingle(
                        //Returns a default item, in case both Remote and Cache couldn't fetch the item
                        //Filtered in #getApods
                        //TODO: Find a better solution than this
                        APOD(
                            date = date.parseDate(),
                            explanation = "",
                            mediaType = APOD.MediaType.IMAGE,
                            title = "Error",
                            url = "",
                            hdUrl = ""
                        )
                    )
            )
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
        }, BackpressureStrategy.BUFFER)
            .concatMap { date ->
                getApod(date)
                    .toFlowable()
                    .filter {
                        //Filter Default items
                        //TODO: Find a btter solution
                        !it.title.equals("error", true)
                    }
            }.toList()
    }

    override fun saveApods(vararg apods: APOD): Completable {
        return cachedDataStore.saveApods(apods.asList())
    }

    override fun cleareApods(): Completable {
        return cachedDataStore.clearApods()
    }
}
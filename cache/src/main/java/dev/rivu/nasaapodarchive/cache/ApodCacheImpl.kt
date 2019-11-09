package dev.rivu.nasaapodarchive.cache

import dev.rivu.nasaapodarchive.cache.dao.ApodDao
import dev.rivu.nasaapodarchive.cache.mapper.ApodEntityMapper
import dev.rivu.nasaapodarchive.data.repository.ApodCache
import dev.rivu.nasaapodarchive.domain.model.APOD
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class ApodCacheImpl @Inject constructor(private val apodDao: ApodDao, private val apodEntityMapper: ApodEntityMapper) :
    ApodCache {

    override fun clearApods(): Completable {
        return apodDao.deleteAllApods()
    }

    override fun saveApods(apods: List<APOD>): Completable {
        return apodDao.saveApods(apods.map(apodEntityMapper::mapFromDomain))
    }

    override fun getApod(date: String): Maybe<APOD> {
        return apodDao.getApod(date)
            .map(apodEntityMapper::mapToDomain)
    }

    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(true)
        }
    }
}
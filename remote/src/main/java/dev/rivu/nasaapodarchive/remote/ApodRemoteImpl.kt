package dev.rivu.nasaapodarchive.remote

import dev.rivu.nasaapodarchive.data.repository.ApodRemote
import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.remote.mapper.ApodMapper
import io.reactivex.Single
import javax.inject.Inject

class ApodRemoteImpl @Inject constructor(private val apodService: ApodService, private val apodMapper: ApodMapper): ApodRemote {
    override fun getApod(date: String): Single<APOD> {
        return apodService.getApod(date)
            .map(apodMapper::mapToDomain)
    }
}
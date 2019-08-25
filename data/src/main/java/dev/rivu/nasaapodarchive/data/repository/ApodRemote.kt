package dev.rivu.nasaapodarchive.data.repository

import dev.rivu.nasaapodarchive.domain.model.APOD
import io.reactivex.Single

interface ApodRemote {


    /**
     * Retrieve a single Apod, from the remote.
     */
    fun getApod(date: String): Single<APOD>
}
package dev.rivu.nasaapodarchive.remote

import dev.rivu.nasaapodarchive.remote.model.ApodResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodService {
    @GET("/planetary/apod?api_key=af4NFhLQo9KVFZW03H55UlVoDQnKKbC7Xk1UD0hk")
    fun getApod(@Query("date") date: String): Single<ApodResponse>
}
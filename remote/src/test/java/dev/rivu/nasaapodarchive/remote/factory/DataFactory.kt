package dev.rivu.nasaapodarchive.remote.factory

import dev.rivu.nasaapodarchive.domain.model.APOD
import dev.rivu.nasaapodarchive.utils.parseDate
import java.util.*


/**
 * Factory class for data instances
 */
public object DataFactory {

    fun randomInt(): Int {
        return kotlin.random.Random.nextInt(0, 1000 + 1)
    }

    fun randomInt(start: Int, end: Int): Int {
        return kotlin.random.Random.nextInt(start, end)
    }

    fun randomLong(): Long {
        return randomInt().toLong()
    }

    fun randomUuid(): String {
        return java.util.UUID.randomUUID().toString()
    }

    fun randomDate(): String {
        return "${randomInt(
            2017,
            2018
        )}-${randomInt(
            10,
            12
        )}-${randomInt(1, 30)}"
    }

    fun randomString(): String {
        val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..500)
            .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    fun randomBoolean(): Boolean {
        return kotlin.random.Random.nextBoolean()
    }

    fun randomMediaType(): String {
        return if(randomBoolean()) {
            APOD.MediaType.IMAGE.value
        } else {
            APOD.MediaType.VIDEO.value
        }
    }

}
package dev.rivu.nasaapodarchive.domain.model.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <T> the input type
 * @param <D> the domain type
 */
interface Mapper<D, T> {

    fun mapFromDomain(type: D): T

    fun mapToDomain(type: T): D

}
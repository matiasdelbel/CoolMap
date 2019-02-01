package com.mdelbel.android.domain.place.city

import com.mdelbel.android.domain.location.Location

open class CityInfo(private val city: City, private val language: String, private val currency: String) {

    fun code() = city.code()

    fun name() = city.name()

    fun countryCode() = city.countryCode()

    fun language() = language

    fun currency() = currency

    fun invokeIfIsMe(city: City, ifIsMe: () -> Unit = {}, ifIsNotMe: () -> Unit = {}) = when (this.city.code()) {
        city.code() -> ifIsMe()
        else -> ifIsNotMe()
    }

    fun asLocationCollection(): List<Location> {
        val cityAsCollectionOfLocations = mutableListOf<Location>()
        city.asAreas().forEach { area ->
            cityAsCollectionOfLocations.addAll(area.asLocationCollection())
        }
        return cityAsCollectionOfLocations
    }
}
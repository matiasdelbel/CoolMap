package com.mdelbel.android.domain.place.city

open class CityInfo(private val city: City, private val language: String, private val currency: String) {

    fun code() = city.code()

    fun name() = city.name()

    fun countryCode() = city.countryCode()

    fun language() = language

    fun currency() = currency

    fun asListOfLatLngPoints() = city.asListOfLatLngPoints()

    fun invokeIfIsMe(nearCity: City, ifIsMe: () -> Unit = {}, ifIsNotMe: () -> Unit = {}) = when (city.code()) {
        nearCity.code() -> ifIsMe()
        else -> ifIsNotMe()
    }
}
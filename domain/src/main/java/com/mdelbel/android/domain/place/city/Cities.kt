package com.mdelbel.android.domain.place.city

import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.Country

class Cities(private val cities: List<City> = emptyList()) {

    fun asCityCollection() = cities

    fun obtainNearTo(country: Country, location: Location): City {
        var nearCity: City = NonExistentCity
        var nearCityDistance = Double.MAX_VALUE

        obtainOn(country).cities.forEach {
            val distanceToLocation = it.distanceTo(location)
            if (distanceToLocation < nearCityDistance) {
                nearCity = it
                nearCityDistance = distanceToLocation
            }
        }

        return nearCity
    }

    fun obtainOn(location: LocationOnCountry): City {
        var matchingCity: City = NonExistentCity
        cities.forEach { city -> city.invokeIfContain(location = location, ifContain = { matchingCity = city }) }

        return matchingCity
    }

    fun obtainOn(country: Country): Cities {
        val citiesOfCountry = mutableListOf<City>()
        cities.forEach { city -> city.invokeIfIn(country = country, ifIsIn = { citiesOfCountry.add(city) }) }

        return Cities(citiesOfCountry)
    }

    fun invokeIfEmpty(ifIsEmpty: () -> Unit, ifIsNotEmpty: () -> Unit) = when (cities.isEmpty()) {
        true -> ifIsEmpty()
        else -> ifIsNotEmpty()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val otherCities = other as Cities

        return otherCities.cities == cities
    }

    override fun hashCode(): Int = cities.hashCode()
}
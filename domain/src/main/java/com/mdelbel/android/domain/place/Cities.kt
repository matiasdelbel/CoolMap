package com.mdelbel.android.domain.place

import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.location.LocationOnCountry

class Cities(private val cities: List<City> = emptyList()) {

    fun pickCityOn(location: LocationOnCountry): City {
        var matchingCity: City = NonExistentCity
        cities.forEach { it.invokeIfContain(locationToCheck = location, ifContain = { matchingCity = it }) }

        return matchingCity
    }

    fun obtainBy(country: Country): Cities {
        val citiesOfCountry = mutableListOf<City>()
        cities.forEach { city -> city.invokeIfFrom(country = country, ifIsFrom = { citiesOfCountry.add(city) }) }

        return Cities(citiesOfCountry)
    }

    fun obtainNearTo(location: Location): City {
        var nearCity = City()
        var distance = Double.MAX_VALUE

        cities.forEach {
            val approxDistance = it.approxDistanceTo(location)
            if (approxDistance < distance) {
                nearCity = it
                distance = approxDistance
            }
        }

        return nearCity
    }

    fun invokeIfEmpty(ifIsEmpty: () -> Unit, ifIsNotEmpty: () -> Unit) = when (cities.isEmpty()) {
        true -> ifIsEmpty()
        else -> ifIsNotEmpty()
    }

    fun asCityDetailsList() = cities

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val otherCities = other as Cities

        return otherCities.cities == cities
    }

    override fun hashCode(): Int = cities.hashCode()
}
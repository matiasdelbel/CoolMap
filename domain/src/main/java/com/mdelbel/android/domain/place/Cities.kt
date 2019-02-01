package com.mdelbel.android.domain.place

import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.city.City
import com.mdelbel.android.domain.place.city.NonExistentCity

class Cities(private val cities: List<City> = emptyList()) {

    fun pickCityOn(location: LocationOnCountry): City {
        var matchingCity: City = NonExistentCity
        cities.forEach { it.invokeIfContain(location = location, ifContain = { matchingCity = it }) }

        return matchingCity
    }

    fun obtainBy(country: Country): Cities {
        val citiesOfCountry = mutableListOf<City>()
        cities.forEach { city -> city.invokeIfIn(country = country, ifIsIn = { citiesOfCountry.add(city) }) }

        return Cities(citiesOfCountry)
    }

    fun obtainNearTo(location: Location): City {
        var nearCity = City("", "", "")
        var distance = Double.MAX_VALUE

        cities.forEach {
            val approxDistance = it.distanceTo(location)
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
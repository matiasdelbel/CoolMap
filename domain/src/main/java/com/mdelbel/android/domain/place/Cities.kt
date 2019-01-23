package com.mdelbel.android.domain.place

import com.mdelbel.android.domain.location.UserLocation

class Cities(private val cities: List<CityDetail> = emptyList()) {

    fun pickCityOn(location: UserLocation): CityDetail {
        val locationToCheck = location.asLocation()
        var matchingCity: CityDetail = NullCity

        cities.forEach {
            it.invokeIfContain(locationToCheck = locationToCheck, ifContain = { matchingCity = it })
        }

        return matchingCity
    }

    fun obtainBy(country: Country): Cities {
        val citiesOfCountry = mutableListOf<CityDetail>()
        cities.forEach { city ->
            city.invokeIfFrom(country = country, ifIsFrom = { citiesOfCountry.add(city) })
        }

        return Cities(citiesOfCountry)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val otherCities = other as Cities

        return otherCities.cities == cities
    }

    override fun hashCode(): Int {
        return cities.hashCode()
    }
}
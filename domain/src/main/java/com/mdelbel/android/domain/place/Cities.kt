package com.mdelbel.android.domain.place

import com.mdelbel.android.domain.location.UserLocation

class Cities(private val cities: List<CityDetail>) {

    fun pickCityOn(location: UserLocation): CityDetail {
        val locationToCheck = location.asLocation()
        var matchingCity: CityDetail = NullCity

        cities.forEach {
            it.invokeIfContain(locationToCheck = locationToCheck, ifContain = { matchingCity = it })
        }

        return matchingCity
    }
}
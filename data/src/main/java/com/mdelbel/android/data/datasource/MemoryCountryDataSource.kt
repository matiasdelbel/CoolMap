package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.location.UserLocation
import com.mdelbel.android.domain.place.Cities

class MemoryCountryDataSource : CityDataSource {

    private var citiesCache: Cities = Cities()

    override fun obtainAll() = citiesCache

    internal fun save(cities: Cities) {
        citiesCache = cities
    }

    internal fun obtainBy(location: UserLocation) = citiesCache.pickCityOn(location)
}
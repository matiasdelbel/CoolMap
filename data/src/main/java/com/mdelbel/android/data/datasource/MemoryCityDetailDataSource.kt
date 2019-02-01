package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.city.Cities
import com.mdelbel.android.domain.place.Country

class MemoryCityDetailDataSource(private var citiesCache: Cities = Cities()) : CitiesDataSource {

    override fun obtainAll() = citiesCache

    internal fun save(cities: Cities) {
        citiesCache = cities
    }

    internal fun obtainBy(location: LocationOnCountry) = citiesCache.obtainOn(location)

    internal fun obtainBy(country: Country) = citiesCache.obtainOn(country)
}
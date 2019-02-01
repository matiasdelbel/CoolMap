package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.Country

class MemoryCityDetailDataSource(private var citiesCache: Cities = Cities()) : CityDetailDataSource {

    override fun obtainAll() = citiesCache

    internal fun save(cities: Cities) {
        citiesCache = cities
    }

    internal fun obtainBy(location: LocationOnCountry) = citiesCache.pickCityOn(location)

    internal fun obtainBy(country: Country) = citiesCache.obtainBy(country)
}
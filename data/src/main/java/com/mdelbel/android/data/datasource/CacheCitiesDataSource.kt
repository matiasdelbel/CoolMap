package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.city.Cities

class CacheCitiesDataSource(private var citiesCache: Cities = Cities()) : CitiesDataSource {

    override fun obtainAll() = citiesCache

    internal fun save(cities: Cities) {
        citiesCache = cities
    }

    internal fun obtainBy(location: LocationOnCountry) = citiesCache.obtainOn(location)

    internal fun obtainBy(location: Location) = citiesCache.obtainNearTo(location)

    internal fun obtainBy(country: Country) = citiesCache.obtainOn(country)
}
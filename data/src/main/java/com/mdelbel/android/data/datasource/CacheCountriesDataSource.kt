package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.Countries

class CacheCountriesDataSource(private var countriesCache: Countries = Countries()) : CountriesDataSource {

    override fun obtainAll() = countriesCache

    internal fun save(countries: Countries) {
        countriesCache = countries
    }
}
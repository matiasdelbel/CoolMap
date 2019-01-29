package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.Countries

class MemoryCountryDataSource(private var countriesCache: Countries = Countries()) : CountryDataSource {

    override fun obtainAll() = countriesCache

    internal fun save(countries: Countries) {
        countriesCache = countries
    }
}
package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.City
import com.mdelbel.android.domain.place.NullCity

class MemoryCityDataSource(private var citiesCache: MutableMap<String, City> = mutableMapOf()) : CityDataSource {

    override fun obtain(cityCode: String): City = when {
        citiesCache[cityCode] == null -> NullCity
        else -> citiesCache[cityCode]!!
    }

    internal fun save(city: City) {
        citiesCache[city.code()] = city
    }
}
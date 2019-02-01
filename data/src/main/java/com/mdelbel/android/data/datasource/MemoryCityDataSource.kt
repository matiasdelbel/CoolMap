package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.city.CityInfo
import com.mdelbel.android.domain.place.city.NoCityInfo

class MemoryCityDataSource(private var citiesCache: MutableMap<String, CityInfo> = mutableMapOf()) : CityDataSource {

    override fun obtain(cityCode: String): CityInfo = when {
        citiesCache[cityCode] == null -> NoCityInfo
        else -> citiesCache[cityCode]!!
    }

    internal fun save(city: CityInfo) {
        citiesCache[city.code()] = city
    }
}
package com.mdelbel.android.data.repository

import com.mdelbel.android.data.datasource.CityDataSource
import com.mdelbel.android.data.datasource.MemoryCityDataSource
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.Country
import io.reactivex.Observable

class CityRepository(private val cache: MemoryCityDataSource, private val origin: CityDataSource) {

    fun obtainAll(): Observable<Cities> {
        // Returns the cache data
        val citiesOnCache = cache.obtainAll()
        // But we refresh the cache with the origin data
        origin.obtainAll().subscribe { cache.save(cities = it) }.dispose()

        return citiesOnCache
    }

    fun obtainBy(country: Country) = cache.obtainBy(country)
}
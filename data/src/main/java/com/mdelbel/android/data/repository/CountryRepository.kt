package com.mdelbel.android.data.repository

import com.mdelbel.android.data.datasource.CountryDataSource
import com.mdelbel.android.data.datasource.MemoryCountryDataSource
import com.mdelbel.android.domain.place.Countries
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepository @Inject constructor(
    private val cache: MemoryCountryDataSource = MemoryCountryDataSource(),
    private val origin: CountryDataSource
) {

    fun obtainAll(): Observable<Countries> {
        return Observable.create {
            val countriesOnCache = cache.obtainAll()
            countriesOnCache.invokeIfEmpty(
                {
                    cache.save(origin.obtainAll())
                    it.onNext(cache.obtainAll())
                },
                { it.onNext(countriesOnCache) })
        }
    }
}
package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.Country
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class MemoryDataSource : CityDataSource {

    private var citiesCache = Cities()

    private lateinit var emitter: ObservableEmitter<Cities>
    private val cache: Observable<Cities> = Observable.create { emitter = it }

    override fun obtainAll(): Observable<Cities> = cache

    internal fun save(cities: Cities) {
        citiesCache = cities
        emitter.onNext(citiesCache)
    }

    internal fun obtainBy(country: Country): Cities = citiesCache.obtainBy(country)
}
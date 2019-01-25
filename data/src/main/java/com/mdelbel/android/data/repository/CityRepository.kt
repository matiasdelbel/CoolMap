package com.mdelbel.android.data.repository

import com.mdelbel.android.data.datasource.CityDataSource
import com.mdelbel.android.data.datasource.MemoryCityDataSource
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.Country
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository @Inject constructor(
    private val cache: MemoryCityDataSource = MemoryCityDataSource(),
    private val origin: CityDataSource
) {

    private lateinit var originDisposable: Disposable

    fun obtainAll(): Observable<Cities> {
        // Returns the cache data
        val citiesOnCache = cache.obtainAll()
        // But we refresh the cache with the origin data
        originDisposable = origin
            .obtainAll()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { cache.save(cities = it); originDisposable.dispose() },
                { originDisposable.dispose() })

        return citiesOnCache
    }

    fun obtainBy(country: Country) = cache.obtainBy(country)
}
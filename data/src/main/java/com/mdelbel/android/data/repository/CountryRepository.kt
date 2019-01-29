package com.mdelbel.android.data.repository

import com.mdelbel.android.data.datasource.CityDataSource
import com.mdelbel.android.data.datasource.MemoryCityDataSource
import com.mdelbel.android.domain.location.UserLocation
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.CityDetail
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepository @Inject constructor(
    private val cache: MemoryCityDataSource = MemoryCityDataSource(),
    private val origin: CityDataSource
) {

    fun obtainAll(): Observable<Cities> {
        return Observable.create {
            val citiesOnCache = cache.obtainAll()
            // TODO reemplazar con un invoke if
            when {
                citiesOnCache.isEmpty() -> {
                    cache.save(origin.obtainAll())
                    it.onNext(cache.obtainAll())
                }
                else -> it.onNext(citiesOnCache)
            }
        }
    }

    //TODO revisar
    fun obtainBy(userLocation: UserLocation): Observable<CityDetail> {
        return Observable.create {
            val citiesOnCache = cache.obtainAll()
            // TODO reemplazar con un invoke if
            when {
                citiesOnCache.isEmpty() -> {
                    cache.save(origin.obtainAll())
                    it.onNext(cache.obtainBy(userLocation))
                }
                else -> it.onNext(cache.obtainBy(userLocation))
            }
        }
    }
}
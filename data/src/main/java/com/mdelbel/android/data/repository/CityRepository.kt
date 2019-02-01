package com.mdelbel.android.data.repository

import com.mdelbel.android.data.datasource.CityDetailDataSource
import com.mdelbel.android.data.datasource.MemoryCityDetailDataSource
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.city.Cities
import com.mdelbel.android.domain.place.city.City
import com.mdelbel.android.domain.place.Country
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository @Inject constructor(
    private val cache: MemoryCityDetailDataSource = MemoryCityDetailDataSource(),
    private val origin: CityDetailDataSource
) {

    fun obtainAll(): Observable<Cities> {
        return Observable.create {
            invokeIfIsOnCache(
                ifCacheIsEmpty = { updateCachePublishingResult(it) },
                ifCacheIsNotEmpty = { it.onNext(cache.obtainAll()) })
        }
    }

    fun obtainBy(userLocation: LocationOnCountry): Observable<City> {
        return Observable.create {
            invokeIfIsOnCache(
                ifCacheIsEmpty = { updateCachePublishingResult(userLocation, it) },
                ifCacheIsNotEmpty = { it.onNext(cache.obtainBy(userLocation)) })
        }
    }

    fun obtainBy(country: Country): Observable<Cities> {
        return Observable.create {
            invokeIfIsOnCache(
                ifCacheIsEmpty = { updateCachePublishingResult(country, it) },
                ifCacheIsNotEmpty = { it.onNext(cache.obtainBy(country)) })
        }
    }

    private fun invokeIfIsOnCache(ifCacheIsEmpty: () -> Unit, ifCacheIsNotEmpty: () -> Unit) {
        val citiesOnCache = cache.obtainAll()
        citiesOnCache.invokeIfEmpty(ifCacheIsEmpty, ifCacheIsNotEmpty)
    }

    private fun updateCachePublishingResult(emitter: ObservableEmitter<Cities>) {
        try {
            cache.save(origin.obtainAll())
            emitter.onNext(cache.obtainAll())
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }

    private fun updateCachePublishingResult(country: Country, emitter: ObservableEmitter<Cities>) {
        try {
            cache.save(origin.obtainAll())
            emitter.onNext(cache.obtainBy(country))
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }

    private fun updateCachePublishingResult(userLocation: LocationOnCountry, emitter: ObservableEmitter<City>) {
        try {
            cache.save(origin.obtainAll())
            emitter.onNext(cache.obtainBy(userLocation))
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}
package com.mdelbel.android.data.repository

import com.mdelbel.android.data.datasource.CityDataSource
import com.mdelbel.android.data.datasource.MemoryCityDataSource
import com.mdelbel.android.domain.location.UserLocation
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.CityDetail
import com.mdelbel.android.domain.place.Country
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository @Inject constructor(
    private val cache: MemoryCityDataSource = MemoryCityDataSource(),
    private val origin: CityDataSource
) {

    fun obtainAll(): Observable<Cities> {
        return Observable.create {
            invokeIfIsOnCache(
                ifCacheIsEmpty = { updateCachePublishingResult(it) },
                ifCacheIsNotEmpty = { it.onNext(cache.obtainAll()) })
        }
    }

    fun obtainBy(userLocation: UserLocation): Observable<CityDetail> {
        return Observable.create {
            invokeIfIsOnCache(
                ifCacheIsEmpty = { updateCachePublishingResult(userLocation, it) },
                ifCacheIsNotEmpty = { it.onNext(cache.obtainBy(userLocation)) })
        }
    }

    fun obtainBy(country: Country): Observable<Cities> {
        return Observable.create {
            invokeIfIsOnCache(
                ifCacheIsEmpty = { updateCachePublishingResult(it) },
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

    private fun updateCachePublishingResult(userLocation: UserLocation, emitter: ObservableEmitter<CityDetail>) {
        try {
            cache.save(origin.obtainAll())
            emitter.onNext(cache.obtainBy(userLocation))
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}
package com.mdelbel.android.data.repository

import com.mdelbel.android.data.datasource.CityDataSource
import com.mdelbel.android.data.datasource.MemoryCityDataSource
import com.mdelbel.android.domain.place.City
import com.mdelbel.android.domain.place.NullCity
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityDetailRepository @Inject constructor(
    private val cache: MemoryCityDataSource = MemoryCityDataSource(),
    private val origin: CityDataSource
) {

    fun obtainBy(cityCode: String): Observable<City> {
        return Observable.create {
            invokeIfIsOnCache(
                cityCode = cityCode,
                ifCacheIsEmpty = { updateCachePublishingResult(cityCode, it) },
                ifCacheIsNotEmpty = { it.onNext(cache.obtain(cityCode)) })
        }
    }

    private fun invokeIfIsOnCache(cityCode: String, ifCacheIsEmpty: () -> Unit, ifCacheIsNotEmpty: () -> Unit) {
        val citiesOnCache = cache.obtain(cityCode)
        when (citiesOnCache) {
            is NullCity -> ifCacheIsEmpty()
            else -> ifCacheIsNotEmpty()
        }
    }

    private fun updateCachePublishingResult(cityCode: String, emitter: ObservableEmitter<City>) {
        try {
            cache.save(origin.obtain(cityCode))
            emitter.onNext(cache.obtain(cityCode))
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}
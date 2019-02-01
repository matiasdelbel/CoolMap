package com.mdelbel.android.data.repository

import com.mdelbel.android.data.datasource.CacheCityInfoDataSource
import com.mdelbel.android.data.datasource.CityInfoDataSource
import com.mdelbel.android.domain.place.city.CityInfo
import com.mdelbel.android.domain.place.city.NoCityInfo
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityDetailRepository @Inject constructor(
    private val cache: CacheCityInfoDataSource = CacheCityInfoDataSource(),
    private val origin: CityInfoDataSource
) {

    fun obtainBy(cityCode: String): Observable<CityInfo> {
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
            is NoCityInfo -> ifCacheIsEmpty()
            else -> ifCacheIsNotEmpty()
        }
    }

    private fun updateCachePublishingResult(cityCode: String, emitter: ObservableEmitter<CityInfo>) {
        try {
            cache.save(origin.obtain(cityCode))
            emitter.onNext(cache.obtain(cityCode))
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}
package com.mdelbel.android.data.repository

import com.mdelbel.android.data.datasource.CacheCountriesDataSource
import com.mdelbel.android.data.datasource.CountriesDataSource
import com.mdelbel.android.domain.place.Countries
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesRepository @Inject constructor(
    private val cache: CacheCountriesDataSource = CacheCountriesDataSource(), private val origin: CountriesDataSource
) {

    fun obtainAll(): Observable<Countries> = Observable.create {
        val countriesOnCache = cache.obtainAll()
        countriesOnCache.invokeIfEmpty(
            ifIsEmpty = { updateCachePublishingResult(it) },
            ifIsNotEmpty = { it.onNext(countriesOnCache) })
    }

    private fun updateCachePublishingResult(emitter: ObservableEmitter<Countries>) = try {
        cache.save(origin.obtainAll())
        emitter.onNext(cache.obtainAll())
    } catch (e: Exception) {
        emitter.onError(e)
    }
}
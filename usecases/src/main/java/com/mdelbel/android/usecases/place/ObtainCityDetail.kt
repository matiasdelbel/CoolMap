package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.repository.CityDetailRepository
import com.mdelbel.android.domain.place.City
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ObtainCityDetail @Inject constructor(private val repository: CityDetailRepository) {

    operator fun invoke(cityCode: String): Observable<City> = repository
        .obtainBy(cityCode)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
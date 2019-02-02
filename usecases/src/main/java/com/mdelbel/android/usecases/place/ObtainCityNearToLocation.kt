package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.repository.CitiesRepository
import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.place.city.City
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ObtainCityNearToLocation @Inject constructor(private val repository: CitiesRepository) {

    operator fun invoke(location: Location): Observable<City> = repository
        .obtainBy(location)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
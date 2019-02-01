package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.repository.CityRepository
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.city.City
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FilterCitiesByLocation @Inject constructor(private val repository: CityRepository) {

    operator fun invoke(userLocation: LocationOnCountry): Observable<City> = repository
        .obtainBy(userLocation)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
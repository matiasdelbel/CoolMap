package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.repository.CityRepository
import com.mdelbel.android.domain.place.city.Cities
import com.mdelbel.android.domain.place.Country
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FilterCitiesByCountry @Inject constructor(private val repository: CityRepository) {

    operator fun invoke(country: Country): Observable<Cities> = repository
        .obtainBy(country)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
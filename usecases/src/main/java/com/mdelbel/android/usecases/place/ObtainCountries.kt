package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.repository.CountryRepository
import com.mdelbel.android.domain.place.Countries
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ObtainCountries @Inject constructor(private val repository: CountryRepository) {

    operator fun invoke(): Observable<Countries> = repository
        .obtainAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
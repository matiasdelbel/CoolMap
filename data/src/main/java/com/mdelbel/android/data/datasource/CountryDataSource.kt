package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.Countries
import io.reactivex.Observable

interface CountryDataSource {

    fun obtainAll(): Observable<Countries>
}
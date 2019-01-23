package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.Country
import io.reactivex.Observable

//TODO agregar un repositoriy
interface CityDataSource {

    fun obtainAll(): Observable<Cities>

    fun obtainBy(country: Country): Observable<Cities>
}
package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.Cities
import io.reactivex.Observable

interface CityDataSource {

    fun obtainAll(): Observable<Cities>
}
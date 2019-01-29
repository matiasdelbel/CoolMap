package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.Cities

interface CityDataSource {

    fun obtainAll(): Cities
}
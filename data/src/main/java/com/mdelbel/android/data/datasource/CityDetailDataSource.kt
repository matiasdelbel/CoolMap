package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.Cities

interface CityDetailDataSource {

    fun obtainAll(): Cities
}
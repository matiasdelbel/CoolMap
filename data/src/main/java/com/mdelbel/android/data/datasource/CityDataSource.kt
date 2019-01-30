package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.City

interface CityDataSource {

    fun obtain(cityCode : String): City
}
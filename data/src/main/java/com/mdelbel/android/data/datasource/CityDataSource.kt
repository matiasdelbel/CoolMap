package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.city.CityInfo

interface CityDataSource {

    fun obtain(cityCode : String): CityInfo
}
package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.CityInfo

interface CityDataSource {

    fun obtain(cityCode : String): CityInfo
}
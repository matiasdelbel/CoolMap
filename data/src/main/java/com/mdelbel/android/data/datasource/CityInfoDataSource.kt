package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.city.CityInfo

interface CityInfoDataSource {

    fun obtain(cityCode : String): CityInfo
}
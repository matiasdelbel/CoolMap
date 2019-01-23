package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.datasource.CityDataSource

class ObtainCities(private val dataSource: CityDataSource) {

    operator fun invoke() = dataSource.obtainAll()
}
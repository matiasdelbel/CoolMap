package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.datasource.CityDataSource
import com.mdelbel.android.domain.place.Country

class FilterCitiesByCountry(private val dataSource: CityDataSource) {

    operator fun invoke(country: Country) = dataSource.obtainBy(country)
}
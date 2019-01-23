package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.datasource.CountryDataSource

class ObtainCountries(private val dataSource: CountryDataSource) {

    operator fun invoke() = dataSource.obtainAll()
}
package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.Countries

interface CountriesDataSource {

    fun obtainAll(): Countries
}
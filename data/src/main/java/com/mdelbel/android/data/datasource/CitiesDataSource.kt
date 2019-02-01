package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.city.Cities

interface CitiesDataSource {

    fun obtainAll(): Cities
}
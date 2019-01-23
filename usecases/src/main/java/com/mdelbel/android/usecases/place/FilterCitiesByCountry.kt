package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.repository.CityRepository
import com.mdelbel.android.domain.place.Country

class FilterCitiesByCountry(private val repository: CityRepository) {

    operator fun invoke(country: Country) = repository.obtainBy(country)
}
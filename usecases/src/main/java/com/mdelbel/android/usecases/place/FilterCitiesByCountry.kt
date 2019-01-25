package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.repository.CityRepository
import com.mdelbel.android.domain.place.Country
import javax.inject.Inject

class FilterCitiesByCountry  @Inject constructor(private val repository: CityRepository) {

    operator fun invoke(country: Country) = repository.obtainBy(country)
}
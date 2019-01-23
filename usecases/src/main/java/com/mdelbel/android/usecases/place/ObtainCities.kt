package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.repository.CityRepository

class ObtainCities(private val repository: CityRepository) {

    operator fun invoke() = repository.obtainAll()
}
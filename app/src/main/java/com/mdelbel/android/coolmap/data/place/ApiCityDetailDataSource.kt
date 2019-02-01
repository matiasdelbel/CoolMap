package com.mdelbel.android.coolmap.data.place

import com.mdelbel.android.data.datasource.CitiesDataSource
import com.mdelbel.android.domain.place.city.Cities
import com.mdelbel.android.domain.place.city.City

class ApiCityDetailDataSource(private val retrofitClient: RetrofitClient = RetrofitClient) : CitiesDataSource {

    override fun obtainAll(): Cities {
        val requestInterface = retrofitClient.createService(PlacesApi::class.java)

        val response = requestInterface.getCities().execute()

        if (response.isSuccessful) {
            val dto = response.body()
            val cities = mutableListOf<City>()
            dto!!.forEach { cityDto -> cities.add(cityDto.asCity()) }
            return Cities(cities)
        } else {
            throw Exception("Cannot load cities")
        }
    }
}
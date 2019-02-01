package com.mdelbel.android.coolmap.data.place

import com.mdelbel.android.data.datasource.CityDetailDataSource
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.City

class ApiCityDetailDataSource(private val retrofitClient: RetrofitClient = RetrofitClient) : CityDetailDataSource {

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
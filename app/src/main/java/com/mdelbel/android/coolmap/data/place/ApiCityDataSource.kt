package com.mdelbel.android.coolmap.data.place

import com.mdelbel.android.data.datasource.CityDataSource
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.CityDetail

class ApiCityDataSource(private val retrofitClient: RetrofitClient = RetrofitClient) : CityDataSource {

    override fun obtainAll(): Cities {
        val requestInterface = retrofitClient.createService(PlacesApi::class.java)

        val response = requestInterface.getCities().execute()

        if (response.isSuccessful) {
            val dto = response.body()
            val cities = mutableListOf<CityDetail>()
            dto!!.forEach { cityDto -> cities.add(cityDto.asCity()) }
            return Cities(cities)
        } else {
            throw Exception("Cannot load cities")
        }
    }
}
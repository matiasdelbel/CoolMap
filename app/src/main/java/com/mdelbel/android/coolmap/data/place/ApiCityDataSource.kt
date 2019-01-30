package com.mdelbel.android.coolmap.data.place

import com.mdelbel.android.data.datasource.CityDataSource
import com.mdelbel.android.domain.place.City

class ApiCityDataSource(private val retrofitClient: RetrofitClient = RetrofitClient) : CityDataSource {

    override fun obtain(cityCode: String): City {
        val requestInterface = retrofitClient.createService(PlacesApi::class.java)

        val response = requestInterface.getCityDetail(cityCode).execute()

        if (response.isSuccessful) {
            val dto = response.body()
            return dto!!.asCity()
        } else {
            throw Exception("Cannot load city information")
        }
    }
}
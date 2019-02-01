package com.mdelbel.android.coolmap.data.place

import com.mdelbel.android.data.datasource.CityInfoDataSource
import com.mdelbel.android.domain.place.city.CityInfo

class ApiCityDataSource(private val retrofitClient: RetrofitClient = RetrofitClient) : CityInfoDataSource {

    override fun obtain(cityCode: String): CityInfo {
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
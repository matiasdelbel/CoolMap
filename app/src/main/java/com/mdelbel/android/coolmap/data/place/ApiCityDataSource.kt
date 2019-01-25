package com.mdelbel.android.coolmap.data.place

import com.mdelbel.android.data.datasource.CityDataSource
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.CityDetail
import io.reactivex.Observable

class ApiCityDataSource(private val retrofitClient: RetrofitClient = RetrofitClient) : CityDataSource {

    //TODO handle error cases
    override fun obtainAll(): Observable<Cities> {
        val requestInterface = retrofitClient.createService(GetCities::class.java)

        return requestInterface.get().map { dtos ->
            val cities = mutableListOf<CityDetail>()
            dtos.forEach { cityDto -> cities.add(cityDto.asCity()) }
            return@map Cities(cities)
        }
    }
}
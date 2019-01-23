package com.mdelbel.android.coolmap.data.place

import com.mdelbel.android.data.datasource.CityDataSource
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.City
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiCityDataSource : CityDataSource {

    //TODO handle error cases
    //TODO pegarle desde el telefono
    override fun obtainAll(): Observable<Cities> {
        val requestInterface = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/api/") //TODO
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GetCities::class.java)

        return requestInterface.get().map {
            val cities = mutableListOf<City>()
            it.forEach { city ->
                cities.add(city.asCity())
            }
            return@map Cities(cities)
        }
    }
}
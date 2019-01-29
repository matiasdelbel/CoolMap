package com.mdelbel.android.coolmap.data.place

import retrofit2.Call
import retrofit2.http.GET

interface PlacesApi {

    @GET("cities")
    fun getCities(): Call<List<CityDto>>

    @GET("countries")
    fun getCountries(): Call<List<CountryDto>>
}
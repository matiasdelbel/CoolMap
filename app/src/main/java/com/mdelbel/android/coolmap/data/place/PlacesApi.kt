package com.mdelbel.android.coolmap.data.place

import com.mdelbel.android.coolmap.data.place.dto.CityDetailDto
import com.mdelbel.android.coolmap.data.place.dto.CityDto
import com.mdelbel.android.coolmap.data.place.dto.CountryDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlacesApi {

    @GET("cities")
    fun getCities(): Call<List<CityDetailDto>>

    @GET("cities/{id}")
    fun getCityDetail(@Path("id") id: String): Call<CityDto>

    @GET("countries")
    fun getCountries(): Call<List<CountryDto>>
}
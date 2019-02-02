package com.mdelbel.android.coolmap.data.place

import com.mdelbel.android.coolmap.data.place.dto.CityDto
import com.mdelbel.android.coolmap.data.place.dto.CityInfoDto
import com.mdelbel.android.coolmap.data.place.dto.CountryDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlacesApi {

    @GET("cities")
    fun getCities(): Call<List<CityDto>>

    @GET("cities/{id}")
    fun getCityInfo(@Path("id") id: String): Call<CityInfoDto>

    @GET("countries")
    fun getCountries(): Call<List<CountryDto>>
}
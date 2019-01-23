package com.mdelbel.android.coolmap.data.place

import io.reactivex.Observable
import retrofit2.http.GET

interface GetCities {

    @GET("cities")
    fun get(): Observable<List<CityDto>>
}
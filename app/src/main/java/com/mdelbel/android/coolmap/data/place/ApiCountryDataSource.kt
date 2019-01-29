package com.mdelbel.android.coolmap.data.place

import com.mdelbel.android.data.datasource.CountryDataSource
import com.mdelbel.android.domain.place.Countries
import com.mdelbel.android.domain.place.Country

class ApiCountryDataSource(private val retrofitClient: RetrofitClient = RetrofitClient) : CountryDataSource {

    override fun obtainAll(): Countries {
        val requestInterface = retrofitClient.createService(PlacesApi::class.java)

        val response = requestInterface.getCountries().execute()

        if (response.isSuccessful) {
            val dto = response.body()
            val countries = mutableListOf<Country>()
            dto!!.forEach { countryDto -> countries.add(countryDto.asCountry()) }
            return Countries(countries)
        } else {
            throw Exception("Cannot load countries")
        }
    }
}
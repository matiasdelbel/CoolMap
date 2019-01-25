package com.mdelbel.android.coolmap.view.destination

import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.CityDetail
import com.mdelbel.android.domain.place.Countries

interface DestinationView {

    fun loading()

    fun goToMap(selected: CityDetail)

    fun showCountries(countries: Countries)

    fun showCities(cities: Cities)

}
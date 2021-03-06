package com.mdelbel.android.coolmap.view.destination

import com.mdelbel.android.coolmap.view.destination.state.MessageError
import com.mdelbel.android.domain.place.Countries
import com.mdelbel.android.domain.place.city.Cities
import com.mdelbel.android.domain.place.city.City

interface SelectDestinationView {

    fun loading()

    fun showCountries(countries: Countries)

    fun showCities(cities: Cities)

    fun goToMap(selected: City)

    fun showError(message: MessageError)

    fun exit()
}
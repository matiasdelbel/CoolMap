package com.mdelbel.android.coolmap.view.destination

import com.mdelbel.android.coolmap.view.destination.state.MessageError
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.city.City
import com.mdelbel.android.domain.place.Countries

interface SelectDestinationView {

    fun loading()

    fun goToMap(selected: City)

    fun showCountries(countries: Countries)

    fun showCities(cities: Cities)

    fun showError(message: MessageError)

    fun exit()
}
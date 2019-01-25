package com.mdelbel.android.coolmap.view.destination.state

import com.mdelbel.android.coolmap.view.destination.DestinationView
import com.mdelbel.android.domain.place.Cities

class PickCityState(private val cities: Cities) : ViewState {

    override fun render(view: DestinationView) = view.showCities(cities)

}
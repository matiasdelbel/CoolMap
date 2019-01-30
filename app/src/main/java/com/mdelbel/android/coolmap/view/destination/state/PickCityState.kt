package com.mdelbel.android.coolmap.view.destination.state

import com.mdelbel.android.coolmap.view.destination.SelectDestinationView
import com.mdelbel.android.domain.place.Cities

class PickCityState(private val cities: Cities) : DestinationViewState {

    override fun render(view: SelectDestinationView) = view.showCities(cities)

}
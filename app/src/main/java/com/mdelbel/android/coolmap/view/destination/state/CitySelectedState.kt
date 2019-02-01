package com.mdelbel.android.coolmap.view.destination.state

import com.mdelbel.android.coolmap.view.destination.SelectDestinationView
import com.mdelbel.android.domain.place.City

class CitySelectedState(private val city: City) : DestinationViewState {

    override fun render(view: SelectDestinationView) = view.goToMap(city)

}
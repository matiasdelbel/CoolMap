package com.mdelbel.android.coolmap.view.map.state

import com.mdelbel.android.coolmap.view.map.MapView
import com.mdelbel.android.domain.place.City

class DisplayCityInformationState(private val city: City) : MapViewState {

    override fun render(view: MapView) {
        view.showCityInformation(city)
    }

}
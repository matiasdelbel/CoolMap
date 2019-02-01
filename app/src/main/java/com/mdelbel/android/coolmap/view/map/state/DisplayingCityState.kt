package com.mdelbel.android.coolmap.view.map.state

import com.mdelbel.android.coolmap.view.map.MapView
import com.mdelbel.android.domain.place.city.CityInfo

class DisplayingCityState(private val city: CityInfo) : MapViewState {

    override fun render(view: MapView) {
        view.showCityInformation(city)
        view.moveTo(city.asListOfLatLngPoints())
    }

}
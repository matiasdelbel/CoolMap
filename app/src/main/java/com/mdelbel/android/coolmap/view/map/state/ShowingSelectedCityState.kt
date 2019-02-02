package com.mdelbel.android.coolmap.view.map.state

import com.google.android.gms.maps.model.LatLng
import com.mdelbel.android.coolmap.view.map.MapView
import com.mdelbel.android.domain.place.city.CityInfo

class ShowingSelectedCityState(private val city: CityInfo) : MapViewState {

    override fun render(view: MapView) {
        view.showCityInformation(city)

        val cityAsCollectionOfLatLng = mutableListOf<LatLng>()
        city.asLocationCollection().forEach { location ->
            cityAsCollectionOfLatLng.add(location.asLatLng())
        }

        view.moveTo(cityAsCollectionOfLatLng)
    }

}
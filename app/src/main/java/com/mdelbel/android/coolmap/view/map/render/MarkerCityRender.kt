package com.mdelbel.android.coolmap.view.map.render

import com.google.android.gms.maps.model.LatLng
import com.mdelbel.android.coolmap.view.map.MapView
import com.mdelbel.android.domain.place.city.Cities

class MarkerCityRender : CityRenderStyle {

    override fun render(cities: Cities, view: MapView) {
        val markers = mutableListOf<LatLng>()
        cities.asCityCollection().forEach { city -> markers.add(city.center().asLatLng()) }

        view.showMarkers(markers)
    }
}
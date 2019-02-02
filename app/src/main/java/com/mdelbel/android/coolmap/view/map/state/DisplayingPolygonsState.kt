package com.mdelbel.android.coolmap.view.map.state

import com.google.android.gms.maps.model.LatLng
import com.mdelbel.android.coolmap.view.map.MapView
import com.mdelbel.android.domain.place.city.Cities
import com.mdelbel.android.domain.place.city.area.Area

class DisplayingPolygonsState(private val cities: Cities) : MapViewState {

    override fun render(view: MapView) {
        val areas = cityAsLatLngCollection()
        view.showWorkingAreas(areas)
    }

    private fun cityAsLatLngCollection(): List<List<LatLng>> {
        val areas = mutableListOf<List<LatLng>>()
        cities.asCityCollection().forEach { city ->
            city.asAreas().forEach { area ->
                areas.add(areaAsLatLngCollection(area))
            }
        }

        return areas
    }

    private fun areaAsLatLngCollection(area: Area): List<LatLng> {
        val areaAsLatLng = mutableListOf<LatLng>()
        area.asLocationCollection().forEach { location ->
            areaAsLatLng.add(location.asLatLng())
        }

        return areaAsLatLng
    }
}
package com.mdelbel.android.coolmap.view.map.render

import com.google.android.gms.maps.model.LatLng
import com.mdelbel.android.coolmap.view.map.MapView
import com.mdelbel.android.domain.place.Cities

class PolygonCityRender : CityRenderStyle {

    override fun render(cities: Cities, view: MapView) {
        val areas = mutableListOf<List<LatLng>>()

        cities.asCityDetailsList().forEach { city ->
            city .asAreas().forEach { area ->
                val areaAsLatLng = mutableListOf<LatLng>()
                area.asLocationCollection().forEach { location ->
                    areaAsLatLng.add(location.asLatLng())
                }

                areas.add(areaAsLatLng)
            }

        }
        view.showWorkingAreas(areas)
    }
}
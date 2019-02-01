package com.mdelbel.android.coolmap.view.map.render

import com.mdelbel.android.coolmap.view.map.MapView
import com.mdelbel.android.domain.place.Area
import com.mdelbel.android.domain.place.Cities

class PolygonCityRender : CityRenderStyle {

    override fun render(cities: Cities, view: MapView) {
        val areas = mutableListOf<Area>()
        cities.asCityDetailsList().forEach {
            areas.addAll(it.asAreas())
        }
        view.showWorkingAreas(areas)
    }
}
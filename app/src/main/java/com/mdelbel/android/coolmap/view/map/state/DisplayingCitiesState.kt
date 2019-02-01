package com.mdelbel.android.coolmap.view.map.state

import com.mdelbel.android.coolmap.view.map.MapView
import com.mdelbel.android.domain.place.Area
import com.mdelbel.android.domain.place.Cities

class DisplayingCitiesState(private val cities: Cities) : MapViewState {

    override fun render(view: MapView) {
        val areas = mutableListOf<Area>()
        cities.asCityDetailsList().forEach {
            areas.addAll(it.asAreas())
        }
        view.showWorkingAreas(areas)
    }
}
package com.mdelbel.android.coolmap.view.map.state

import com.mdelbel.android.coolmap.view.map.MapView
import com.mdelbel.android.coolmap.view.map.render.CityRenderStyle
import com.mdelbel.android.domain.place.Cities

class DisplayingCitiesState(private val cities: Cities, private val render: CityRenderStyle) : MapViewState {

    override fun render(view: MapView) {
        render.render(cities, view)
    }
}
package com.mdelbel.android.coolmap.view.map.render

import com.mdelbel.android.coolmap.view.map.MapView
import com.mdelbel.android.domain.place.Cities

class MarkerCityRender : CityRenderStyle {

    override fun render(cities: Cities, view: MapView) {
        view.showCities(cities)
    }
}
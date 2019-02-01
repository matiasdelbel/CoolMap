package com.mdelbel.android.coolmap.view.map.render

import com.mdelbel.android.coolmap.view.map.MapView
import com.mdelbel.android.domain.place.Cities

interface CityRenderStyle{

    fun render(cities: Cities, view: MapView)
}
package com.mdelbel.android.coolmap.view.map

import com.google.android.gms.maps.model.LatLng
import com.mdelbel.android.coolmap.view.map.state.MessageError
import com.mdelbel.android.domain.place.Area
import com.mdelbel.android.domain.place.City

interface MapView {

    fun showCityInformation(city: City)

    fun showWorkingAreas(city: City, areas: List<Area>)

    fun moveTo(locations: List<LatLng>)

    fun loading()

    fun showError(error: MessageError)
}
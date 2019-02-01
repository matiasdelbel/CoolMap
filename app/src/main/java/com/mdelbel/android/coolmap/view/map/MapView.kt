package com.mdelbel.android.coolmap.view.map

import com.google.android.gms.maps.model.LatLng
import com.mdelbel.android.coolmap.view.map.state.MessageError
import com.mdelbel.android.domain.place.Area
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.City

interface MapView {

    fun showCityInformation(city: City)

    fun showWorkingAreas(areas: List<Area>)

    fun showCities(cities: Cities)

    fun moveTo(locations: List<LatLng>)

    fun loading()

    fun showError(error: MessageError)
}
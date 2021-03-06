package com.mdelbel.android.coolmap.view.map

import com.google.android.gms.maps.model.LatLng
import com.mdelbel.android.coolmap.view.map.state.MessageError
import com.mdelbel.android.domain.place.city.CityInfo

interface MapView {

    fun showCityInformation(city: CityInfo)

    fun showWorkingAreas(areas: List<List<LatLng>>)

    fun showMarkers(points: List<LatLng>)

    fun moveTo(locations: List<LatLng>)

    fun loading()

    fun showError(error: MessageError)
}
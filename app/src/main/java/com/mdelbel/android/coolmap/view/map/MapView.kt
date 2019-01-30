package com.mdelbel.android.coolmap.view.map

import com.google.android.gms.maps.model.LatLng
import com.mdelbel.android.coolmap.view.map.state.MessageError
import com.mdelbel.android.domain.place.City

interface MapView {

    fun showCityInformation(city: City)

    fun moveTo(locations: List<LatLng>)

    fun loading()

    fun showError(error: MessageError)
}
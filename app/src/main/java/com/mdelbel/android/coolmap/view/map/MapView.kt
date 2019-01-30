package com.mdelbel.android.coolmap.view.map

import com.mdelbel.android.coolmap.view.map.state.MessageError
import com.mdelbel.android.domain.place.City

interface MapView {

    fun loading()

    fun showError(error: MessageError)

    fun showCityInformation(city: City)
}
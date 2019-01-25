package com.mdelbel.android.coolmap.view.destination.state

import com.mdelbel.android.coolmap.view.destination.DestinationView
import com.mdelbel.android.domain.place.CityDetail

class CitySelectedState(private val city: CityDetail) : ViewState {

    override fun render(view: DestinationView) = view.goToMap(city)

}
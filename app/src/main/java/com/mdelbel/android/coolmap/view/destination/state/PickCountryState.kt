package com.mdelbel.android.coolmap.view.destination.state

import com.mdelbel.android.coolmap.view.destination.SelectDestinationView
import com.mdelbel.android.domain.place.Countries

class PickCountryState(private val countries: Countries) : ViewState {

    override fun render(view: SelectDestinationView) = view.showCountries(countries)
}
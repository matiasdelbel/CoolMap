package com.mdelbel.android.coolmap.view.ddestination.state

import com.mdelbel.android.coolmap.view.destination.DestinationView
import com.mdelbel.android.coolmap.view.destination.state.PickCountryState
import com.mdelbel.android.domain.place.Countries
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class PickCountryStateTest {

    @Test
    fun `render should call show countries on view`() {
        val view = mock(DestinationView::class.java)
        val countries = Countries()
        val pickCountryState = PickCountryState(countries)

        pickCountryState.render(view)

        verify(view).showCountries(countries)
    }
}
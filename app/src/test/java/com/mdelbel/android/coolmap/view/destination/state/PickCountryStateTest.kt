package com.mdelbel.android.coolmap.view.destination.state

import com.mdelbel.android.coolmap.view.destination.SelectDestinationView
import com.mdelbel.android.domain.place.Countries
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class PickCountryStateTest {

    @Test
    fun `render should call show countries on view`() {
        val view = mock(SelectDestinationView::class.java)
        val countries = Countries()
        val pickCountryState = PickCountryState(countries)

        pickCountryState.render(view)

        verify(view).showCountries(countries)
    }
}
package com.mdelbel.android.coolmap.view.ddestination.state

import com.mdelbel.android.coolmap.view.destination.DestinationView
import com.mdelbel.android.coolmap.view.destination.state.PickCityState
import com.mdelbel.android.domain.place.Cities
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class PickCityStateTest {

    @Test
    fun `render should call pick city on view`() {
        val view = mock(DestinationView::class.java)
        val cities = Cities()
        val pickCityState = PickCityState(cities)

        pickCityState.render(view)

        verify(view).showCities(cities)
    }
}
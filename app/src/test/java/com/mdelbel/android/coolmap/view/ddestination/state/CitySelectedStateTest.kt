package com.mdelbel.android.coolmap.view.ddestination.state

import com.mdelbel.android.coolmap.view.destination.SelectDestinationView
import com.mdelbel.android.coolmap.view.destination.state.CitySelectedState
import com.mdelbel.android.domain.place.city.City
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito

class CitySelectedStateTest {

    @Test
    fun `render should call go to map on view`() {
        val view = Mockito.mock(SelectDestinationView::class.java)
        val city = City()
        val citySelectedState = CitySelectedState(city)

        citySelectedState.render(view)

        verify(view).goToMap(city)
    }
}
package com.mdelbel.android.coolmap.view.destination.state

import com.mdelbel.android.coolmap.view.destination.SelectDestinationView
import com.mdelbel.android.domain.place.city.City
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class CitySelectedStateTest {

    @Test
    fun `render should call go to map on view`() {
        val view = mock(SelectDestinationView::class.java)
        val city = mock(City::class.java)
        val citySelectedState = CitySelectedState(city)

        citySelectedState.render(view)

        verify(view).goToMap(city)
    }
}
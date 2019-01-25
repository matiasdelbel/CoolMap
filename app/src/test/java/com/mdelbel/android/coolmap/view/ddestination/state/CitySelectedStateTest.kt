package com.mdelbel.android.coolmap.view.ddestination.state

import com.mdelbel.android.coolmap.view.destination.DestinationView
import com.mdelbel.android.coolmap.view.destination.state.CitySelectedState
import com.mdelbel.android.domain.place.CityDetail
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito

class CitySelectedStateTest {

    @Test
    fun `render should call go to map on view`() {
        val view = Mockito.mock(DestinationView::class.java)
        val city = CityDetail()
        val citySelectedState = CitySelectedState(city)

        citySelectedState.render(view)

        verify(view).goToMap(city)
    }
}
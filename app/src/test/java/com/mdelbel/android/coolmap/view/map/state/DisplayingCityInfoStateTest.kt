package com.mdelbel.android.coolmap.view.map.state

import com.mdelbel.android.coolmap.view.map.MapView
import com.mdelbel.android.domain.place.city.CityInfo
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class DisplayingCityInfoStateTest {

    @Test
    fun `render should call go to show city information on view`() {
        val view = mock(MapView::class.java)
        val city = mock(CityInfo::class.java)
        val citySelectedState = DisplayingCityInfoState(city)

        citySelectedState.render(view)

        verify(view).showCityInformation(city)
    }
}
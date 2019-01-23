package com.mdelbel.android.domain.data.place

import com.mdelbel.android.domain.place.CityDetail
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.Location
import com.mdelbel.android.domain.place.WorkingArea
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class CityDetailTest {

    @Test
    fun `invoke if contain should delegate task to working area`() {
        val location = mock(Location::class.java)
        val ifContain: () -> Unit = {}
        val ifNotContain: () -> Unit = {}
        val workingArea = mock(WorkingArea::class.java)
        val cityDetail = CityDetail(workingArea = workingArea)

        cityDetail.invokeIfContain(location, ifContain, ifNotContain)

        verify(workingArea).invokeIfContain(location, ifContain, ifNotContain)
    }

    @Test
    fun `invoke if from should delegate task to country`() {
        val country = mock(Country::class.java)
        val ifIsFrom: () -> Unit = {}
        val ifIsNotFrom: () -> Unit = {}
        val cityDetail = CityDetail(countryCode = "AR")

        cityDetail.invokeIfFrom(country, ifIsFrom, ifIsNotFrom)

        verify(country).invokeIfMe("AR", ifIsFrom, ifIsNotFrom)
    }
}
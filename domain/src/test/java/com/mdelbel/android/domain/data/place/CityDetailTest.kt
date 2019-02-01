package com.mdelbel.android.domain.data.place

import com.mdelbel.android.domain.place.CityDetail
import com.mdelbel.android.domain.place.Country
import org.junit.Test
import org.mockito.Mockito.mock

class CityDetailTest {

    @Test
    fun `invoke if from should delegate task to country`() {
        val country = mock(Country::class.java)
        val ifIsFrom: () -> Unit = {}
        val ifIsNotFrom: () -> Unit = {}
        val cityDetail = CityDetail(countryCode = "AR")

        cityDetail.invokeIfFrom(country, ifIsFrom, ifIsNotFrom)

        //verify(country).invokeIfMe("AR", ifIsFrom, ifIsNotFrom)
    }
}
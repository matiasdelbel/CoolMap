package com.mdelbel.android.domain.data.place

import com.mdelbel.android.domain.place.Location
import com.mdelbel.android.domain.place.NullCity
import org.junit.Assert.fail
import org.junit.Test
import org.mockito.Mockito.mock

class NullCityTest {

    @Test
    fun `invoke if contain should invoke not contains`() {
        val location = mock(Location::class.java)

        NullCity.invokeIfContain(location, { fail() }, { assert(true) })

    }
}
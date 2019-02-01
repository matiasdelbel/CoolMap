package com.mdelbel.android.domain.data.location

import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.Country
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import org.mockito.Mockito.mock

class UserLocationTest {

    @Test
    fun `user location as location should return its`() {
        val expected = mock(Location::class.java)
        val country = mock(Country::class.java)
        val userLocation = LocationOnCountry(expected, country)

        val location = userLocation.asLocation()

        assertEquals(expected, location)
    }

    @Test
    fun `invoke if is on country with location not on country should invoke if not`() {
        val country = Country(code = "AR")
        val userLocation = LocationOnCountry(mock(Location::class.java), country)

        userLocation.invokeIfIamOn(Country(code = "BR"), { fail() }, { assert(true) })
    }

    @Test
    fun `invoke if is on country with location on country should invoke if in`() {
        val country = Country(code = "AR")
        val userLocation = LocationOnCountry(mock(Location::class.java), country)

        userLocation.invokeIfIamOn(country, { assert(true) }, { fail() })
    }
}
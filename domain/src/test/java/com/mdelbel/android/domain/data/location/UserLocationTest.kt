package com.mdelbel.android.domain.data.location

import com.mdelbel.android.domain.location.UserLocation
import com.mdelbel.android.domain.place.Location
import org.junit.Assert.assertEquals
import org.junit.Test

class UserLocationTest {

    @Test
    fun `user location as location should create a location with the same coordinates`() {
        val expected = Location(LATITUDE, LONGITUDE)
        val userLocation = UserLocation(LATITUDE, LONGITUDE)

        val location = userLocation.asLocation()

        assertEquals(expected, location)
    }
}

private const val LATITUDE = 50.4
private const val LONGITUDE = 100.0
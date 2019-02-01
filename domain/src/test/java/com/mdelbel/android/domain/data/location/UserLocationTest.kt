package com.mdelbel.android.domain.data.location

import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.location.Location
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

class UserLocationTest {

    @Test
    fun `user location as location should create a location with the same coordinates`() {
        val expected = Location(LATITUDE, LONGITUDE)
        val userLocation = LocationOnCountry(LATITUDE, LONGITUDE, COUNTRY)

        val location = userLocation.asLocation()

        assertEquals(expected, location)
    }

    @Test
    fun `invoke if is on country with location not on country should invoke if not in country`() {
        val userLocation = LocationOnCountry(LATITUDE, LONGITUDE, Country("AR", "Argentina"))

        userLocation.invokeIfIamOn("BR", { fail() }, { assert(true) })
    }

    @Test
    fun `invoke if is on country with location on country should invoke if in country`() {
        val userLocation = LocationOnCountry(LATITUDE, LONGITUDE, Country("AR", "Argentina"))

        userLocation.invokeIfIamOn("AR", { assert(true) }, { fail() })
    }
}

private const val LATITUDE = 50.4
private const val LONGITUDE = 100.0
private val COUNTRY = Country()
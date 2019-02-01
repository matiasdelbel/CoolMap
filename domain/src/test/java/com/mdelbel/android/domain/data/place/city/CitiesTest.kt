package com.mdelbel.android.domain.data.place.city

import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.place.city.Cities
import com.mdelbel.android.domain.place.city.City
import com.mdelbel.android.domain.place.city.DistanceMeter
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class CitiesTest {

    @Test
    fun `obtain near to should returns closer city`() {
        val location = mock(Location::class.java)
        // Mock the cities
        val closerCity = mock(City::class.java)
        whenever(closerCity.distanceTo(location, DistanceMeter)).thenReturn(1.0)
        val otherCity = mock(City::class.java)
        whenever(otherCity.distanceTo(location, DistanceMeter)).thenReturn(3.0)
        // Create the cities
        val cities = Cities(listOf(closerCity, otherCity))

        val result = cities.obtainNearTo(location)

        assertEquals(closerCity, result)
    }
}
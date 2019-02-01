package com.mdelbel.android.domain.data.place.city

import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.city.City
import com.mdelbel.android.domain.place.city.DistanceMeter
import com.mdelbel.android.domain.place.city.area.AreaProcessor
import com.mdelbel.android.domain.place.city.area.WorkingArea
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class CityTest {

    @Test
    fun `code, name and countryCode should returns the data from the country`() {
        val buenosAires = City("BUE", "Buenos Aires", "AR")

        assertEquals("BUE", buenosAires.code())
        assertEquals("Buenos Aires", buenosAires.name())
        assertEquals("AR", buenosAires.countryCode())
    }

    @Test
    fun `center and asAreas should delegate task to working area`() {
        val workingArea = mock(WorkingArea::class.java)
        val buenosAires = City("BUE", "Buenos Aires", "AR", workingArea)

        buenosAires.center()
        buenosAires.asAreas()

        verify(workingArea).center(AreaProcessor)
        verify(workingArea).asAreas()
    }

    @Test
    fun `invoke if in should delegate task to country`() {
        val country = mock(Country::class.java)
        val ifIsIn: () -> Unit = {}
        val ifIsNotIn: () -> Unit = {}
        val buenosAires = City("BUE", "Buenos Aires", "AR")

        buenosAires.invokeIfIn(country, ifIsIn, ifIsNotIn)

        verify(country).invokeIfMe(Country("AR"), ifIsIn, ifIsNotIn)
    }

    @Test
    fun `distance to location should delegate task to distance meter`() {
        // Mock the location to ask and the center of the working area
        val location = mock(Location::class.java)
        val center = mock(Location::class.java)
        // Mock the center area
        val workingArea = mock(WorkingArea::class.java)
        whenever(workingArea.center(AreaProcessor)).thenReturn(center)
        // Mock the distance meter
        val distanceMeter = mock(DistanceMeter::class.java)
        whenever(distanceMeter.computeBetween(center, location)).thenReturn(5.0)
        // Create the city
        val buenosAires = City("BUE", "Buenos Aires", "AR", workingArea)

        val distance = buenosAires.distanceTo(location, distanceMeter)

        assertEquals(5.0, distance, 0.0)
    }
}
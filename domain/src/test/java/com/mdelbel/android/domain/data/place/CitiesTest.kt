package com.mdelbel.android.domain.data.place

import com.mdelbel.android.domain.location.UserLocation
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.CityDetail
import com.mdelbel.android.domain.place.Location
import com.mdelbel.android.domain.place.NullCity
import org.junit.Assert.assertEquals
import org.junit.Test

class CitiesTest {

    @Test
    fun `pick city for not matching location should returns null city`() {
        val city = CityDetailNotContainLocationMock()
        val cities = Cities(listOf(city))
        val userLocation = location()

        val result = cities.pickCityOn(userLocation)

        assertEquals(NullCity, result)
    }

    @Test
    fun `pick city for matching location should returns it`() {
        val expected = CityDetailContainLocationMock()
        val cities = Cities(listOf(expected))
        val userLocation = location()

        val result = cities.pickCityOn(userLocation)

        assertEquals(expected, result)
    }

    private fun location() = UserLocation(2.2, 2.2)
}

class CityDetailContainLocationMock : CityDetail() {

    override fun invokeIfContain(locationToCheck: Location, ifContain: () -> Unit, ifNotContain: () -> Unit) {
        ifContain()
    }
}

class CityDetailNotContainLocationMock : CityDetail() {

    override fun invokeIfContain(locationToCheck: Location, ifContain: () -> Unit, ifNotContain: () -> Unit) {
        ifNotContain()
    }
}
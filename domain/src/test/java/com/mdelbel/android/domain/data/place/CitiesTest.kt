package com.mdelbel.android.domain.data.place

import com.mdelbel.android.domain.location.UserLocation
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.CityDetail
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.NullCity
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
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

    @Test
    fun `obtain by country should filter countries by country id`() {
        val cityFiltered = CityDetail(countryCode = "AR")
        val cityNotFiltered = CityDetail(countryCode = "BR")
        val expected = Cities(listOf(cityFiltered))
        val cities = Cities(listOf(cityFiltered, cityNotFiltered))

        val result = cities.obtainBy(Country(code = "AR", name = "Argentina"))

        assertEquals(expected, result)
    }

    @Test
    fun `invoke if empty with empty cities should invoke empty`() {
        val cities = Cities()

        cities.invokeIfEmpty(ifIsEmpty = { assert(true) }, ifIsNotEmpty = { fail() })
    }

    @Test
    fun `invoke if empty with not empty cities should invoke not empty`() {
        val cities = Cities(listOf(CityDetail()))

        cities.invokeIfEmpty(ifIsEmpty = { fail() }, ifIsNotEmpty = { assert(true) })
    }

    private fun location() = UserLocation(2.2, 8.2, Country())
}

class CityDetailContainLocationMock : CityDetail() {

    override fun invokeIfContain(locationToCheck: UserLocation, ifContain: () -> Unit, ifNotContain: () -> Unit) {
        ifContain()
    }
}

class CityDetailNotContainLocationMock : CityDetail() {

    override fun invokeIfContain(locationToCheck: UserLocation, ifContain: () -> Unit, ifNotContain: () -> Unit) {
        ifNotContain()
    }
}
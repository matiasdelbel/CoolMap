package com.mdelbel.android.coolmap.data.place.dto

import org.junit.Assert.assertEquals
import org.junit.Test

class CountryDtoTest {

    @Test
    fun `as country should return a new country`() {
        val countryDto = CountryDto("code", "name")

        val country = countryDto.asCountry()

        assertEquals("code", country.code())
        assertEquals("name", country.name())
    }
}
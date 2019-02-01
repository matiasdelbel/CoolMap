package com.mdelbel.android.domain.data.place.city

import com.mdelbel.android.domain.place.city.NonExistentCity
import org.junit.Assert.assertEquals
import org.junit.Test

class NonExistentCityTest {

    @Test
    fun `code, name and countryCode should returns unknown`() {
        assertEquals("unknown", NonExistentCity.code())
        assertEquals("unknown", NonExistentCity.name())
        assertEquals("unknown", NonExistentCity.countryCode())
    }
}
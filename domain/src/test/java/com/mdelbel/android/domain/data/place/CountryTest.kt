package com.mdelbel.android.domain.data.place

import com.mdelbel.android.domain.place.Country
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

class CountryTest {

    @Test
    fun `code, name should returns the data from the country`() {
        val argentina = Country("AR", "Argentina")

        assertEquals("AR", argentina.code())
        assertEquals("Argentina", argentina.name())
    }

    @Test
    fun `invoke if me with another country code should invoke not me`() {
        val argentina = Country("AR", "Argentina")

        argentina.invokeIfMe(Country(code = "BR"), { fail() }, { assert(true) })
    }

    @Test
    fun `invoke if me with country code should invoke is me`() {
        val argentina = Country("AR", "Argentina")

        argentina.invokeIfMe(Country(code = "AR"), { assert(true) }, { fail() })
    }
}
package com.mdelbel.android.domain.data.place.city

import com.mdelbel.android.domain.place.city.City
import com.mdelbel.android.domain.place.city.CityInfo
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

class CityInfoTest {

    @Test
    fun `code, name and countryCode should returns the data from the city`() {
        val buenosAires = City("BUE", "Buenos Aires", "AR")
        val buenosAiresInfo = CityInfo(buenosAires, "es", "ARS")

        assertEquals("BUE", buenosAiresInfo.code())
        assertEquals("Buenos Aires", buenosAiresInfo.name())
        assertEquals("AR", buenosAiresInfo.countryCode())
    }

    @Test
    fun `language and currency should returns the data from the city`() {
        val buenosAires = City("BUE", "Buenos Aires", "AR")
        val buenosAiresInfo = CityInfo(buenosAires, "es", "ARS")

        assertEquals("es", buenosAiresInfo.language())
        assertEquals("ARS", buenosAiresInfo.currency())
    }

    @Test
    fun `invoke if is me with other city should invoke if not`() {
        val buenosAires = City("BUE", "Buenos Aires", "AR")
        val otherCity = City("CODE", "City Name", "AR")
        val buenosAiresInfo = CityInfo(buenosAires, "es", "ARS")

        buenosAiresInfo.invokeIfIsMe(otherCity, ifIsMe = { fail() }, ifIsNotMe = { assert(true) })
    }

    @Test
    fun `invoke if is me with my city should invoke if in`() {
        val buenosAires = City("BUE", "Buenos Aires", "AR")
        val buenosAiresInfo = CityInfo(buenosAires, "es", "ARS")

        buenosAiresInfo.invokeIfIsMe(buenosAires, ifIsMe = { assert(true) }, ifIsNotMe = { fail() })
    }
}
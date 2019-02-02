package com.mdelbel.android.coolmap.data.place.dto

import com.mdelbel.android.domain.place.city.area.WorkingArea
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class CityInfoDtoTest {

    @Test
    fun `as country should return a new country`() {
        val workingAreaDecoder = mock(WorkingAreaDecoder::class.java)
        val workingArea = mock(WorkingArea::class.java)
        whenever(workingAreaDecoder.decode(listOf("tanxBp`_kG"))).thenReturn(workingArea)
        val cityInfoDto = CityInfoDto("code", "name", "country_code", "language", "currency", listOf("tanxBp`_kG"))

        val city = cityInfoDto.asCity(workingAreaDecoder)

        assertEquals("code", city.code())
        assertEquals("name", city.name())
        assertEquals("country_code", city.countryCode())
        assertEquals("language", city.language())
        assertEquals("currency", city.currency())
    }
}
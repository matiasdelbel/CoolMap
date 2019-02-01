package com.mdelbel.android.domain.data.place.city.area

import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.place.city.area.Area
import com.mdelbel.android.domain.place.city.area.AreaProcessor
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class AreaTest {

    @Test
    fun `contain should delegate task to processor`() {
        val area = Area()
        val locationToCheck = mock(Location::class.java)
        val processor = mock(AreaProcessor::class.java)
        whenever(processor.contains(locationToCheck, area)).thenReturn(true)

        assert(area.contain(locationToCheck, processor))
    }

    @Test
    fun `as location collections should returns polygon`() {
        val polygon = listOf<Location>(mock(Location::class.java))
        val area = Area(polygon)

        val points = area.asLocationCollection()

        assertEquals(polygon, points)
    }

}
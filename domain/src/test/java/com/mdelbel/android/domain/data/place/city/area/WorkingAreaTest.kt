package com.mdelbel.android.domain.data.place.city.area

import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.place.city.area.Area
import com.mdelbel.android.domain.place.city.area.AreaProcessor
import com.mdelbel.android.domain.place.city.area.WorkingArea
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import org.mockito.Mockito.mock

class WorkingAreaTest {

    @Test
    fun `invoke if reaches for not matching location should invoke not reaches`() {
        val location = mock(Location::class.java)
        val area = mock(Area::class.java)
        whenever(area.contain(eq(location), any())).thenReturn(false)
        val workingArea = WorkingArea(listOf(area))

        workingArea.invokeIfReaches(location, { fail() }, { assert(true) })
    }

    @Test
    fun `invoke if reaches matching location should invoke reaches`() {
        val location = mock(Location::class.java)
        val area = mock(Area::class.java)
        whenever(area.contain(eq(location), any())).thenReturn(true)
        val workingArea = WorkingArea(listOf(area))

        workingArea.invokeIfReaches(location, { assert(true) }, { fail() })
    }

    @Test
    fun `as areas collections should returns the areas`() {
        val expectedAreas = listOf<Area>(mock(Area::class.java))
        val workingArea = WorkingArea(expectedAreas)

        val areas = workingArea.asAreas()

        assertEquals(expectedAreas, areas)
    }

    @Test
    fun `center should delegate task to processor`() {
        val area = WorkingArea()
        val locationExpected = mock(Location::class.java)
        val processor = mock(AreaProcessor::class.java)
        whenever(processor.centerOf(area)).thenReturn(locationExpected)

        assertEquals(locationExpected, area.center(processor))
    }
}
package com.mdelbel.android.domain.data.place

import com.mdelbel.android.domain.place.Area
import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.place.WorkingArea
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.fail
import org.junit.Test
import org.mockito.Mockito.mock

class WorkingAreaTest {

    @Test
    fun `invoke if contain for not matching location should invoke not contain`() {
        val location = mock(Location::class.java)
        val area = mock(Area::class.java)
        whenever(area.contain(eq(location), any())).thenReturn(false)
        val workingArea = WorkingArea(listOf(area))

        workingArea.invokeIfContain(location, { fail() }, { assert(true) })
    }

    @Test
    fun `invoke if contain matching location should invoke contain`() {
        val location = mock(Location::class.java)
        val area = mock(Area::class.java)
        whenever(area.contain(eq(location), any())).thenReturn(true)
        val workingArea = WorkingArea(listOf(area))

        workingArea.invokeIfContain(location, { assert(true) }, { fail() })
    }
}
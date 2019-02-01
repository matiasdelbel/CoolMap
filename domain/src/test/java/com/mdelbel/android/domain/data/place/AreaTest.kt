package com.mdelbel.android.domain.data.place

import com.google.android.gms.maps.model.LatLng
import com.mdelbel.android.domain.place.Area
import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.place.PolygonChecker
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class AreaTest {

    @Test
    fun `contain should delegate to checker`() {
        val area = Area()
        val locationToCheck = mock(Location::class.java)
        val checker = mock(PolygonChecker::class.java)
        whenever(checker.contains(locationToCheck, area)).thenReturn(CHECKER_RESULT)

        assert(area.contain(locationToCheck, checker))
    }

    @Test
    fun `as lat lng points should returns area as a point collections`() {
        val locationOnArea = Location(LATITUDE, LONGITUDE)
        val area = Area(polygon = listOf(locationOnArea))

        val points = area.asLatLngPoints()

        assertEquals(1, points.size)
        assertEquals(LatLng(LATITUDE, LONGITUDE), points[0])
    }

}

private const val CHECKER_RESULT = true
private const val LATITUDE = 54.3
private const val LONGITUDE = 234.3
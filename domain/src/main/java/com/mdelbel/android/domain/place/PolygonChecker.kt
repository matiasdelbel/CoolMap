package com.mdelbel.android.domain.place

import com.google.maps.android.PolyUtil

class PolygonChecker {

    fun contains(pointToCheck: Location, areaToCheck: Area): Boolean {
        val pointToCheckAsLatLng = pointToCheck.asLatLng()
        val areaAsLatLng = areaToCheck.asLatLngPoints()

        return PolyUtil.containsLocation(pointToCheckAsLatLng, areaAsLatLng, false)
    }
}
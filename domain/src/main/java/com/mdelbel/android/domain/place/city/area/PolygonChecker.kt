package com.mdelbel.android.domain.place.city.area

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil
import com.mdelbel.android.domain.location.Location

class PolygonChecker {

    fun contains(pointToCheck: Location, areaToCheck: Area): Boolean {
        // Convert the location to a LatLng
        val pointToCheckAsLatLng = pointToCheck.asLatLng()

        // Convert the area as a collection of LatLng
        val areaAsLatLng = mutableListOf<LatLng>()
        areaToCheck.asLocationCollection().forEach {
            areaAsLatLng.add(it.asLatLng())
        }

        return PolyUtil.containsLocation(pointToCheckAsLatLng, areaAsLatLng, false)
    }
}
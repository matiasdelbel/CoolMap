package com.mdelbel.android.domain.place.city.area

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.PolyUtil
import com.mdelbel.android.domain.location.Location

class AreaProcessor {

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

    fun centerOf(workingArea: WorkingArea): Location {
        val latLngBoundsBuilder = LatLngBounds.Builder()
        // Iterate all over the areas
        workingArea.asAreas().forEach { area ->
            // Obtain the locations on the area
            area.asLocationCollection().forEach { location ->
                // And add to the builder
                latLngBoundsBuilder.include(location.asLatLng())
            }
        }
        // Create the bounds. Useful to obtain the center of the working area
        val latLngBounds = latLngBoundsBuilder.build()

        return Location(latLngBounds.center.latitude, latLngBounds.center.longitude)
    }
}
package com.mdelbel.android.domain.place.city.area

import com.google.android.gms.maps.model.LatLng
import com.mdelbel.android.domain.location.Location

class WorkingArea(private val areas: List<Area> = emptyList()) {

    fun invokeIfContain(location: Location, ifContain: () -> Unit, ifNotContain: () -> Unit) {
        val matchingArea = areas.firstOrNull { it.contain(location) }

        when (matchingArea) {
            null -> ifNotContain()
            else -> ifContain()
        }
    }

    fun asAreas() = areas

    fun getRepresentativePoint() = areas[0].asLatLngPoints()[0] //TODO

    fun asListOfLatLngPoints(): List<LatLng> {
        val locations = mutableListOf<LatLng>()
        for (area in areas) {
            locations.addAll(area.asLatLngPoints())
        }

        return locations
    }
}
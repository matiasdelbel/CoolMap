package com.mdelbel.android.domain.place

import com.google.android.gms.maps.model.LatLng

class WorkingArea(private val areas: List<Area> = emptyList()) {

    fun invokeIfContain(location: Location, ifContain: () -> Unit, ifNotContain: () -> Unit) {
        val matchingArea = areas.firstOrNull { it.contain(location) }

        when (matchingArea) {
            null -> ifNotContain()
            else -> ifContain()
        }
    }

    fun asListOfLatLngPoints(): List<LatLng> {
        val locations = mutableListOf<LatLng>()
        for (area in areas) {
            locations.addAll(area.asLatLngPoints())
        }

        return locations
    }
}
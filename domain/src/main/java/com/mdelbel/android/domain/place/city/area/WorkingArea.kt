package com.mdelbel.android.domain.place.city.area

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

    fun getRepresentativePoint() = areas[0].asLocationCollection()[0] //TODO

    fun asLocationCollection(): List<Location> {
        val locations = mutableListOf<Location>()
        for (area in areas) {
            locations.addAll(area.asLocationCollection())
        }

        return locations
    }
}
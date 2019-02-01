package com.mdelbel.android.domain.place.city.area

import com.mdelbel.android.domain.location.Location

class WorkingArea(private val areas: List<Area> = emptyList()) {

    fun asAreas() = areas

    fun invokeIfReaches(location: Location, ifReaches: () -> Unit, ifNotReaches: () -> Unit) {
        val matchingArea = areas.firstOrNull { area -> area.contain(location) }
        when (matchingArea) {
            null -> ifNotReaches()
            else -> ifReaches()
        }
    }

    fun center(areaProcessor: AreaProcessor = AreaProcessor) = areaProcessor.centerOf(this)

}
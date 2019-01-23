package com.mdelbel.android.domain.place

class WorkingArea(private val areas: List<Area> = emptyList()) {

    fun invokeIfContain(location: Location, ifContain: () -> Unit, ifNotContain: () -> Unit) {
        val matchingArea = areas.firstOrNull { it.contain(location) }

        when (matchingArea) {
            null -> ifNotContain()
            else -> ifContain()
        }
    }
}
package com.mdelbel.android.domain.place.city.area

import com.mdelbel.android.domain.location.Location

class Area(private val polygon: List<Location> = emptyList()) {

    fun asLocationCollection() = polygon

    fun contain(location: Location, areaProcessor: AreaProcessor = AreaProcessor) =
        areaProcessor.contains(location, this)
}
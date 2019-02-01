package com.mdelbel.android.domain.place.city.area

import com.google.android.gms.maps.model.LatLng
import com.mdelbel.android.domain.location.Location

class Area(private val polygon: List<Location> = emptyList()) {

    fun contain(location: Location, polygonChecker: PolygonChecker = PolygonChecker()) =
        polygonChecker.contains(location, this)

    fun asLatLngPoints(): List<LatLng> {
        val areaAsLatLng = mutableListOf<LatLng>()
        polygon.forEach { areaAsLatLng.add(it.asLatLng()) }

        return areaAsLatLng
    }
}
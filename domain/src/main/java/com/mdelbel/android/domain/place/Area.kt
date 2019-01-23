package com.mdelbel.android.domain.place

import com.google.android.gms.maps.model.LatLng

class Area(private val polygon: List<Location> = emptyList()) {

    fun contain(location: Location, polygonChecker: PolygonChecker = PolygonChecker()) =
        polygonChecker.contains(location, this)

    fun asLatLngPoints(): List<LatLng> {
        val areaAsLatLng = mutableListOf<LatLng>()
        polygon.forEach { areaAsLatLng.add(it.asLatLng()) }

        return areaAsLatLng
    }
}
package com.mdelbel.android.domain.place

import com.google.android.gms.maps.model.LatLng

class Location(private val latitude: Double, private val longitude: Double) {

    fun asLatLng() = LatLng(latitude, longitude)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val otherLocation = other as Location
        return latitude == otherLocation.latitude && longitude == otherLocation.longitude
    }

    override fun hashCode(): Int {
        return latitude.hashCode() + longitude.hashCode()
    }
}
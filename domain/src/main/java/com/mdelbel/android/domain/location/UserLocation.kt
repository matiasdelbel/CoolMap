package com.mdelbel.android.domain.location

import com.mdelbel.android.domain.place.Location

open class UserLocation(
    private val latitude: Double,
    private val longitude: Double,
    private val city: String = "",
    private val country: String = ""
) {

    fun asLocation() = Location(latitude, longitude)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false

        val otherLocation = other as UserLocation
        return latitude == otherLocation.latitude && longitude == otherLocation.longitude
    }

    override fun hashCode(): Int {
        return latitude.hashCode() + longitude.hashCode()
    }
}
package com.mdelbel.android.domain.location

import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.Location

//TODO rename
class UserLocation(
    private val latitude: Double = 0.0,
    private val longitude: Double = 0.0,
    private val country: Country = Country()
) {

    fun asLocation() = Location(latitude, longitude)

    fun invokeIfOnCountry(countryCode: String, ifIsOnCountry: () -> Unit, ifIsNotOnCountry: () -> Unit) =
        country.invokeIfMe(countryCode = countryCode, ifIsMe = ifIsOnCountry, ifIsNotMe = ifIsNotOnCountry)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false

        val otherLocation = other as UserLocation
        return latitude == otherLocation.latitude && longitude == otherLocation.longitude
    }

    override fun hashCode() = latitude.hashCode() + longitude.hashCode()
}
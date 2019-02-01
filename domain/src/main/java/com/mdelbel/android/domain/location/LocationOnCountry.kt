package com.mdelbel.android.domain.location

import com.mdelbel.android.domain.place.Country

class LocationOnCountry(private val location: Location, private val country: Country = Country()) {

    fun asLocation() = location

    fun invokeIfIamOn(country: Country, ifIAmOn: () -> Unit, ifIAmNotOn: () -> Unit) =
        this.country.invokeIfMe(country = country, ifIsMe = ifIAmOn, ifIsNotMe = ifIAmNotOn)

    override fun equals(other: Any?) = (location == other)

    override fun hashCode() = location.hashCode()
}
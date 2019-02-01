package com.mdelbel.android.domain.place

import com.google.maps.android.SphericalUtil
import com.mdelbel.android.domain.location.UserLocation

open class CityDetail(
    private val code: String = "",
    private val name: String = "",
    private val countryCode: String = "",
    private val workingArea: WorkingArea = WorkingArea()
) {

    fun code() = code

    fun name() = name

    fun countryCode() = countryCode

    fun asAreas() = workingArea.asAreas()

    open fun invokeIfContain(locationToCheck: UserLocation, ifContain: () -> Unit = {}, ifNotContain: () -> Unit = {}) {
        val location = locationToCheck.asLocation()
        val ifWorkingAreaContain = { locationToCheck.invokeIfOnCountry(countryCode, ifContain, ifNotContain) }

        workingArea.invokeIfContain(location, ifWorkingAreaContain, ifNotContain)
    }

    open fun invokeIfFrom(country: Country, ifIsFrom: () -> Unit = {}, ifIsNotFrom: () -> Unit = {}) =
        country.invokeIfMe(countryCode, ifIsFrom, ifIsNotFrom)

    fun approxDistanceTo(location: Location): Double {
        return SphericalUtil.computeDistanceBetween(workingArea.getRepresentativePoint(), location.asLatLng())
    }

    fun bla() = workingArea.getRepresentativePoint() //TODO

}
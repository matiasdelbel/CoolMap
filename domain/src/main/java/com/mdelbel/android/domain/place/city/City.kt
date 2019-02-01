package com.mdelbel.android.domain.place.city

import com.google.maps.android.SphericalUtil
import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.city.area.WorkingArea

open class City(
    private val code: String = "",
    private val name: String = "",
    private val countryCode: String = "",
    private val workingArea: WorkingArea = WorkingArea()
) {

    fun code() = code

    fun name() = name

    fun countryCode() = countryCode

    fun asAreas() = workingArea.asAreas()

    open fun invokeIfContain(
        locationToCheck: LocationOnCountry,
        ifContain: () -> Unit = {},
        ifNotContain: () -> Unit = {}
    ) {
        val location = locationToCheck.asLocation()
        val ifWorkingAreaContain =
            { locationToCheck.invokeIfIamOn(Country(code = countryCode), ifContain, ifNotContain) }

        workingArea.invokeIfContain(location, ifWorkingAreaContain, ifNotContain)
    }

    open fun invokeIfFrom(country: Country, ifIsFrom: () -> Unit = {}, ifIsNotFrom: () -> Unit = {}) =
        country.invokeIfMe(Country(code = countryCode), ifIsFrom, ifIsNotFrom)

    fun approxDistanceTo(location: Location): Double {
        return SphericalUtil.computeDistanceBetween(workingArea.getRepresentativePoint().asLatLng(), location.asLatLng()) //TODO
    }

    fun getRepresentativePoint() = workingArea.getRepresentativePoint() //TODO

    fun asListOfLatLngPoints() = workingArea.asLocationCollection()

}
package com.mdelbel.android.domain.place.city

import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.city.area.WorkingArea

open class City(
    private val code: String,
    private val name: String,
    private val countryCode: String,
    private val workingArea: WorkingArea = WorkingArea()
) {

    fun code() = code

    fun name() = name

    fun countryCode() = countryCode

    fun center() = workingArea.center()

    fun asAreas() = workingArea.asAreas()

    fun invokeIfContain(location: LocationOnCountry, ifContain: () -> Unit = {}, ifNotContain: () -> Unit = {}) {
        val latLong = location.asLocation()
        val ifReaches = { location.invokeIfIamOn(Country(code = countryCode), ifContain, ifNotContain) }

        workingArea.invokeIfReaches(latLong, ifReaches = ifReaches, ifNotReaches = ifNotContain)
    }

    fun invokeIfIn(country: Country, ifIsIn: () -> Unit = {}, ifIsNotIn: () -> Unit = {}) =
        country.invokeIfMe(Country(code = countryCode), ifIsIn, ifIsNotIn)

    fun distanceTo(location: Location, distanceMeter: DistanceMeter = DistanceMeter) =
        distanceMeter.computeBetween(workingArea.center(), location)
}
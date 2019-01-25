package com.mdelbel.android.domain.place

import com.mdelbel.android.domain.location.UserLocation

open class CityDetail(
    private val code: String = "",
    private val name: String = "",
    private val countryCode: String = "",
    private val workingArea: WorkingArea = WorkingArea()
) {

    fun code() = code

    fun name() = name

    open fun invokeIfContain(locationToCheck: UserLocation, ifContain: () -> Unit = {}, ifNotContain: () -> Unit = {}) {
        workingArea.invokeIfContain(
            locationToCheck.asLocation(),
            { locationToCheck.invokeIfOnCountry(countryCode, ifContain, ifNotContain) },
            ifNotContain
        )
    }
}
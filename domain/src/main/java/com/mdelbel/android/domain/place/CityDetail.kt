package com.mdelbel.android.domain.place

open class CityDetail(
    private val code: String = "",
    private val name: String = "",
    private val countryCode: String = "",
    private val workingArea: WorkingArea = WorkingArea()
) {

    open fun invokeIfContain(locationToCheck: Location, ifContain: () -> Unit = {}, ifNotContain: () -> Unit = {}) {
        workingArea.invokeIfContain(locationToCheck, ifContain, ifNotContain)
    }
}
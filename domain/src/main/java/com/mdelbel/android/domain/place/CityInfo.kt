package com.mdelbel.android.domain.place

open class CityInfo(
    private val code: String,
    private val name: String,
    private val countryCode: String,
    private val language: String,
    private val currency: String,
    private val workingArea: WorkingArea = WorkingArea() //TODO eliminar
) {

    fun code() = code

    fun name() = name

    fun countryCode() = countryCode

    fun language() = language

    fun currency() = currency

    fun asListOfLatLngPoints() = workingArea.asListOfLatLngPoints()

    fun invokeIfIsMe(nearCity: City, ifIsMe: () -> Unit = {}, ifIsNotMe: () -> Unit = {}) = when (code) {
        nearCity.code() -> ifIsMe()
        else -> ifIsNotMe()
    }
}
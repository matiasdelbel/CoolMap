package com.mdelbel.android.domain.place

class Countries(private val countries: List<Country> = emptyList()) {

    fun invokeIfEmpty(ifIsEmpty: () -> Unit, ifIsNotEmpty: () -> Unit) = when (countries.isEmpty()) {
        true -> ifIsEmpty()
        else -> ifIsNotEmpty()
    }

    fun asCountryList() = countries
}
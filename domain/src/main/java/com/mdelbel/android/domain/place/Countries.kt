package com.mdelbel.android.domain.place

class Countries(private val countries: List<Country> = emptyList()) {

    fun asCountryCollection() = countries

    fun invokeIfEmpty(ifIsEmpty: () -> Unit, ifIsNotEmpty: () -> Unit) = when (countries.isEmpty()) {
        true -> ifIsEmpty()
        else -> ifIsNotEmpty()
    }
}
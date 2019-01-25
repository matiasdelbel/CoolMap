package com.mdelbel.android.domain.place

class Countries(private val countries: List<Country> = emptyList()) {

    fun asCountryList() = countries
}
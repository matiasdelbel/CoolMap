package com.mdelbel.android.domain.place

class Country(private val code: String = "", private val name: String = "") {

    fun code() = code

    fun name() = name

    fun invokeIfMe(country: Country, ifIsMe: () -> Unit, ifIsNotMe: () -> Unit) {
        when (code) {
            country.code -> ifIsMe()
            else -> ifIsNotMe()
        }
    }
}
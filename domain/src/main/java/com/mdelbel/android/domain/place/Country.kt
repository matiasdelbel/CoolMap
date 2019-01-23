package com.mdelbel.android.domain.place

class Country(private val code: String, private val name: String) {

    fun invokeIfMe(countryCode: String, ifIsMe: () -> Unit, ifIsNotMe: () -> Unit) {
        when (code) {
            countryCode -> ifIsMe()
            else -> ifIsNotMe()
        }
    }
}
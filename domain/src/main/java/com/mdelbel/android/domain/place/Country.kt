package com.mdelbel.android.domain.place

class Country(private val code: String, private val name: String) {

    fun code() = code

    fun name() = name

    fun invokeIfMe(countryCode: String, ifIsMe: () -> Unit, ifIsNotMe: () -> Unit) {
        when (code) {
            countryCode -> ifIsMe()
            else -> ifIsNotMe()
        }
    }
}
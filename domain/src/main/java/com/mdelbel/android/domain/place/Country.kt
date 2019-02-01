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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val otherCountry = other as Country
        return code == otherCountry.code && code == otherCountry.code
    }

    override fun hashCode() = code.hashCode()
}
package com.mdelbel.android.coolmap.data.place

import com.mdelbel.android.domain.place.Country

/**
 *
 * {
 *   "code": "BR",
 *   "name": "Brazil"
 * }
 *
 */
data class CountryDto(val code: String, val name: String) {

    fun asCountry() = Country(code, name)
}
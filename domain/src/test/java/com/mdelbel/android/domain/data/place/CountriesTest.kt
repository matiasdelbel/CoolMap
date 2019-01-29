package com.mdelbel.android.domain.data.place

import com.mdelbel.android.domain.place.Countries
import com.mdelbel.android.domain.place.Country
import org.junit.Assert.fail
import org.junit.Test

class CountriesTest {

    @Test
    fun `invoke if empty with empty countries should invoke empty`() {
        val countries = Countries()

        countries.invokeIfEmpty(ifIsEmpty = { assert(true) }, ifIsNotEmpty = { fail() })
    }

    @Test
    fun `invoke if empty with not empty countries should invoke not empty`() {
        val countries = Countries(listOf(Country()))

        countries.invokeIfEmpty(ifIsEmpty = { fail() }, ifIsNotEmpty = { assert(true) })
    }
}
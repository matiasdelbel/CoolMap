package com.mdelbel.android.domain.data.place

import com.mdelbel.android.domain.place.Country
import org.junit.Assert.fail
import org.junit.Test

class CountryTest {

    @Test
    fun `invoke if me with another country code should invoke not me`() {
        val country = Country(code = "AR", name = "Argentina")

        country.invokeIfMe("BR", { fail() }, { assert(true) })
    }

    @Test
    fun `invoke if me with country code should invoke is me`() {
        val country = Country(code = "AR", name = "Argentina")

        country.invokeIfMe("AR", { assert(true) }, { fail() })
    }
}
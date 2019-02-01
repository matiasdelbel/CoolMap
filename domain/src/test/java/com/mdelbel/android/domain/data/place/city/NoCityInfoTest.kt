package com.mdelbel.android.domain.data.place.city

import com.mdelbel.android.domain.place.city.NoCityInfo
import org.junit.Assert.assertEquals
import org.junit.Test

class NoCityInfoTest {

    @Test
    fun `language and currency should returns unknown`() {
        assertEquals("-", NoCityInfo.language())
        assertEquals("-", NoCityInfo.currency())
    }
}
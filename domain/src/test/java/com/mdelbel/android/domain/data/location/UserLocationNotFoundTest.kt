package com.mdelbel.android.domain.data.location

import com.mdelbel.android.domain.location.UserLocation
import com.mdelbel.android.domain.location.UserLocationNoFounded
import org.junit.Assert.assertEquals
import org.junit.Test

class UserLocationNotFoundTest {

    @Test
    fun `user location not founded should returns a null location`() {
        val expected = UserLocation(NO_LATITUDE, NO_LONGITUDE)

        assertEquals(expected, UserLocationNoFounded)
    }
}

private const val NO_LATITUDE = 0.0
private const val NO_LONGITUDE = 0.0

package com.mdelbel.android.usecases.location

import com.mdelbel.android.data.requester.LocationRequester
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class ObtainLocationTest {

    @Test
    fun `invoke should call request location on location requester`() {
        val requester = mock(LocationRequester::class.java)
        val useCase = ObtainLocation(requester)

        useCase()

        verify(requester).requestLocation()
    }
}
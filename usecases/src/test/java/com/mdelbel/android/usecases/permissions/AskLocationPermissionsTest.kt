package com.mdelbel.android.usecases.permissions

import com.mdelbel.android.data.permissions.PermissionsRequester
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class AskLocationPermissionsTest {

    @Test
    fun `invoke should call ask for location permission on permission requester`() {
        val requester = mock(PermissionsRequester::class.java)
        val useCase = AskLocationPermissions(requester)

        useCase()

        verify(requester).requestLocationPermissions()
    }
}
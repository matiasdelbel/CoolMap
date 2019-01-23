package com.mdelbel.android.coolmap.data.permissions

import androidx.appcompat.app.AppCompatActivity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class RequesterActivityBinderTest {

    @Test
    fun `connect should attach activity to requester`() {
        val permissionsRequester = mock(MapPermissionsRequester::class.java)
        val requesterActivity = mock(AppCompatActivity::class.java)
        val binder = RequesterActivityBinder(permissionsRequester, requesterActivity)

        binder.connect()

        verify(permissionsRequester).attach(requesterActivity)
    }

    @Test
    fun `disconnect should detach activity to requester`() {
        val permissionsRequester = mock(MapPermissionsRequester::class.java)
        val requesterActivity = mock(AppCompatActivity::class.java)
        val binder = RequesterActivityBinder(permissionsRequester, requesterActivity)

        binder.disconnect()

        verify(permissionsRequester).detachActivity()
    }

}
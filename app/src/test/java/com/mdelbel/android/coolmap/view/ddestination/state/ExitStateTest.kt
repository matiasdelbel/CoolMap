package com.mdelbel.android.coolmap.view.ddestination.state

import com.mdelbel.android.coolmap.view.destination.SelectDestinationView
import com.mdelbel.android.coolmap.view.destination.state.ExitState
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class ExitStateTest {

    @Test
    fun `render should call exit on view`() {
        val view = mock(SelectDestinationView::class.java)
        val exitState = ExitState

        exitState.render(view)

        verify(view).exit()
    }
}
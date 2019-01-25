package com.mdelbel.android.coolmap.view.ddestination.state

import com.mdelbel.android.coolmap.view.destination.DestinationView
import com.mdelbel.android.coolmap.view.destination.state.LoadingState
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class LoadingStateTest {

    @Test
    fun `render should call loading on view`() {
        val view = mock(DestinationView::class.java)
        val loadingState = LoadingState()

        loadingState.render(view)

        verify(view).loading()
    }
}
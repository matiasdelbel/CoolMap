package com.mdelbel.android.coolmap.view.destination.state

import com.mdelbel.android.coolmap.view.destination.SelectDestinationView
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class LoadingStateTest {

    @Test
    fun `render should call loading on view`() {
        val view = mock(SelectDestinationView::class.java)
        val loadingState = LoadingState()

        loadingState.render(view)

        verify(view).loading()
    }
}
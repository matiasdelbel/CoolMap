package com.mdelbel.android.coolmap.view.map.state

import com.mdelbel.android.coolmap.view.map.MapView
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class LoadingStateTest {

    @Test
    fun `render should call loading on view`() {
        val view = mock(MapView::class.java)

        LoadingState.render(view)

        verify(view).loading()
    }
}
package com.mdelbel.android.coolmap.view.destination.state

import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

class StateStackTest {

    @Test
    fun `dequeue with queue state should return it`() {
        val staState = StateStack()
        val queueState = Mockito.mock(DestinationViewState::class.java)
        staState.queue(queueState)

        val result = staState.dequeue()

        assertEquals(queueState, result)
    }

    @Test
    fun `dequeue with no queue state should return exit state`() {
        val staState = StateStack()

        val result = staState.dequeue()

        assertEquals(ExitState, result)
    }
}
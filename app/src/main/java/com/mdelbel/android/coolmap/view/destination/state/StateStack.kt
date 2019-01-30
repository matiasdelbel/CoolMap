package com.mdelbel.android.coolmap.view.destination.state

import java.util.*

class StateStack(private val queue: Queue<DestinationViewState> = LinkedList()) {

    fun queue(state: DestinationViewState) = queue.add(state)

    fun dequeue(): DestinationViewState {
        val state = queue.poll()
        return when (state) {
            null -> ExitState
            else -> state
        }
    }
}
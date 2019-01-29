package com.mdelbel.android.coolmap.view.destination.state

import java.util.*

class StateStack(private val queue: Queue<ViewState> = LinkedList()) {

    fun queue(state: ViewState) = queue.add(state)

    fun dequeue(): ViewState {
        val state = queue.poll()
        return when (state) {
            null -> ExitState
            else -> state
        }
    }
}
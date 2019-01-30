package com.mdelbel.android.coolmap.view.destination.state

import com.mdelbel.android.coolmap.view.destination.SelectDestinationView

object ExitState : DestinationViewState {

    override fun render(view: SelectDestinationView) = view.exit()
}
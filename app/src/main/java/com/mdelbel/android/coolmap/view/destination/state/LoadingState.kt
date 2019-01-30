package com.mdelbel.android.coolmap.view.destination.state

import com.mdelbel.android.coolmap.view.destination.SelectDestinationView

class LoadingState : DestinationViewState {

    override fun render(view: SelectDestinationView) = view.loading()

}
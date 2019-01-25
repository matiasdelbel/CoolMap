package com.mdelbel.android.coolmap.view.destination.state

import com.mdelbel.android.coolmap.view.destination.DestinationView

class LoadingState : ViewState {

    override fun render(view: DestinationView) = view.loading()

}
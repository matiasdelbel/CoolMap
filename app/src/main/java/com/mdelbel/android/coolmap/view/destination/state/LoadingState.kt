package com.mdelbel.android.coolmap.view.destination.state

import com.mdelbel.android.coolmap.view.destination.SelectDestinationView

class LoadingState : ViewState {

    override fun render(view: SelectDestinationView) = view.loading()

}
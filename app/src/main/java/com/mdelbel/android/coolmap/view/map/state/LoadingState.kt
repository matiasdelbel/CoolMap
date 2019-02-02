package com.mdelbel.android.coolmap.view.map.state

import com.mdelbel.android.coolmap.view.map.MapView

object LoadingState : MapViewState {

    override fun render(view: MapView) = view.loading()

}
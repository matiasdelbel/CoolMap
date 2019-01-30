package com.mdelbel.android.coolmap.view.map.state

import com.mdelbel.android.coolmap.view.map.MapView

class LoadingState : MapViewState {

    override fun render(view: MapView) = view.loading()

}
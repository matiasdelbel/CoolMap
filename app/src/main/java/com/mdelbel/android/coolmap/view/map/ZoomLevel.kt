package com.mdelbel.android.coolmap.view.map

import androidx.lifecycle.MutableLiveData
import com.mdelbel.android.coolmap.view.map.state.DisplayingMarkersState
import com.mdelbel.android.coolmap.view.map.state.DisplayingPolygonsState
import com.mdelbel.android.coolmap.view.map.state.MapViewState
import com.mdelbel.android.domain.place.city.Cities

class ZoomLevel(private var state: Float = Float.MIN_VALUE) {

    fun update(newZoomLevel: ZoomLevel, cities: Cities, viewState: MutableLiveData<MapViewState>) {
        // https://developers.google.com/maps/documentation/android-sdk/views#zoom
        when {
            state > 10 && newZoomLevel.state < 10 -> {
                viewState.postValue(DisplayingMarkersState(cities))
                state = newZoomLevel.state
            }
            state <= 10 && newZoomLevel.state >= 10 -> {
                viewState.postValue(DisplayingPolygonsState(cities))
                state = newZoomLevel.state
            }
        }
    }
}
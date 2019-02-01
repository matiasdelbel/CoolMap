package com.mdelbel.android.coolmap.view.map

import androidx.lifecycle.MutableLiveData
import com.mdelbel.android.coolmap.view.map.render.MarkerCityRender
import com.mdelbel.android.coolmap.view.map.render.PolygonCityRender
import com.mdelbel.android.coolmap.view.map.state.DisplayingCitiesState
import com.mdelbel.android.coolmap.view.map.state.MapViewState
import com.mdelbel.android.domain.place.Cities

class ZoomLevel(private var state: Float = Float.MIN_VALUE) {

    fun update(newZoomLevel: ZoomLevel, cities: Cities, viewState: MutableLiveData<MapViewState>) {
        // https://developers.google.com/maps/documentation/android-sdk/views#zoom
        when {
            state > 10 && newZoomLevel.state < 10 -> {
                viewState.postValue(DisplayingCitiesState(cities, MarkerCityRender()))
                state = newZoomLevel.state
            }
            state <= 10 && newZoomLevel.state >= 10 -> {
                viewState.postValue(DisplayingCitiesState(cities, PolygonCityRender()))
                state = newZoomLevel.state
            }
        }
    }
}
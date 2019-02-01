package com.mdelbel.android.coolmap.view.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mdelbel.android.coolmap.view.map.render.PolygonCityRender
import com.mdelbel.android.coolmap.view.map.state.*
import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.*
import com.mdelbel.android.domain.place.city.CityInfo
import com.mdelbel.android.domain.place.city.NoCityInfo
import com.mdelbel.android.usecases.place.FilterCitiesByCountry
import com.mdelbel.android.usecases.place.ObtainCityDetail
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val obtainCityDetailUseCase: ObtainCityDetail,
    private val filterCitiesByCountryUseCase: FilterCitiesByCountry
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    internal val screenState = MutableLiveData<MapViewState>().apply { setValue(LoadingState()) }

    private var selectedCity: CityInfo =
        NoCityInfo
    private var cities: Cities = Cities()

    private var zoom = ZoomLevel()

    fun obtainGeolocationCityInformation(cityCode: String) {
        obtainCityDetail(cityCode) { screenState.postValue(DisplayingCityState(it)) }
    }

    fun obtainCitiesFor(country: Country) {
        compositeDisposable.add(filterCitiesByCountryUseCase(country).subscribe(
            {
                cities = it
                screenState.postValue(DisplayingCitiesState(it, PolygonCityRender()))
            }, {})
        )
    }

    fun obtainCitiesFor(location: LocationOnCountry) {
        val selected = cities.pickCityOn(location)
        obtainGeolocationCityInformation(selected.code())
    }

    fun onNewCenter(center: Location) {
        val nearCity = cities.obtainNearTo(center)
        selectedCity.invokeIfIsMe(
            nearCity,
            ifIsNotMe = { obtainCityDetail(nearCity.code()) { screenState.postValue(DisplayingCityInfoState(it)) } })
    }

    fun onNewZoomLevel(zoomLevel: ZoomLevel) = zoom.update(zoomLevel, cities, screenState)

    private fun obtainCityDetail(cityCode: String, success: (result: CityInfo) -> Unit) { //TODO revisaar
        compositeDisposable.add(
            obtainCityDetailUseCase(cityCode)
                .subscribe({
                    selectedCity = it
                    success(selectedCity)
                }, {
                    screenState.postValue(CityInformationNoFoundedErrorState)
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
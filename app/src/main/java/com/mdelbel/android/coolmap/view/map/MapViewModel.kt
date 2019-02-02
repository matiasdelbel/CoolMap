package com.mdelbel.android.coolmap.view.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mdelbel.android.coolmap.view.map.state.*
import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.city.CityInfo
import com.mdelbel.android.domain.place.city.NoCityInfo
import com.mdelbel.android.usecases.place.FilterCitiesByCountry
import com.mdelbel.android.usecases.place.FilterCitiesByLocation
import com.mdelbel.android.usecases.place.ObtainCityDetail
import com.mdelbel.android.usecases.place.ObtainCityNearToLocation
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val obtainCityDetailUseCase: ObtainCityDetail,
    private val filterCitiesByCountryUseCase: FilterCitiesByCountry,
    private val filterCitiesByLocationUseCase: FilterCitiesByLocation,
    private val obtainCityNearToLocationUseCase: ObtainCityNearToLocation
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    internal val screenState = MutableLiveData<MapViewState>().apply { setValue(LoadingState) }

    private var selectedCity: CityInfo = NoCityInfo
    private var zoom = ZoomLevel()

    fun obtainGeolocationCityInformation(cityCode: String) =
        obtainCityDetail(cityCode) { screenState.postValue(ShowingSelectedCityState(it)) }

    fun obtainCitiesFor(country: Country) {
        compositeDisposable.add(
            filterCitiesByCountryUseCase(country).subscribe({ screenState.postValue(DisplayingPolygonsState(it)) }, {})
        )
    }

    fun obtainCitiesFor(location: LocationOnCountry) {
        compositeDisposable.add(
            filterCitiesByLocationUseCase(location).subscribe({ obtainGeolocationCityInformation(it.code()) }, {})
        )
    }

    fun onNewCenter(center: Location) {
        compositeDisposable.add(
            obtainCityNearToLocationUseCase(center).subscribe({ nearCity ->
                selectedCity.invokeIfIsMe(
                    nearCity,
                    ifIsNotMe = { obtainCityDetail(nearCity.code()) { screenState.postValue(DisplayingCityInfoState(it)) } })
            }, {})
        )
    }

    fun onNewZoomLevel(country: Country, zoomLevel: ZoomLevel) {
        compositeDisposable.add(
            filterCitiesByCountryUseCase(country).subscribe({ zoom.update(zoomLevel, it, screenState) }, {})
        )
    }

    private fun obtainCityDetail(cityCode: String, success: (result: CityInfo) -> Unit) {
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
package com.mdelbel.android.coolmap.view.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mdelbel.android.coolmap.view.map.state.*
import com.mdelbel.android.domain.place.*
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

    private var selectedCity: City = NullCity
    private var cities: Cities = Cities()

    fun obtainGeolocationCityInformation(cityCode: String) {
        obtainCityDetail(cityCode) { screenState.postValue(DisplayingCityState(it)) }
    }

    fun obtainCitiesFor(country: Country) {
        compositeDisposable.add(filterCitiesByCountryUseCase(country).subscribe(
            {
                cities = it
                screenState.postValue(DisplayingCitiesState(it))
            }, {})
        )
    }

    fun onNewCenter(center: Location) {
        val nearCity = cities.obtainNearTo(center)
        selectedCity.invokeIfIsMe(
            nearCity,
            ifIsNotMe = { obtainCityDetail(nearCity.code()) { screenState.postValue(DisplayingCityInfoState(it)) } })
    }

    private fun obtainCityDetail(cityCode: String, success: (result: City) -> Unit) { //TODO revisaar
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
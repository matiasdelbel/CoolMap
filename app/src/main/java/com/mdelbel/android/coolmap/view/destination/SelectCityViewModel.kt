package com.mdelbel.android.coolmap.view.destination

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mdelbel.android.coolmap.view.destination.state.CitySelectedState
import com.mdelbel.android.coolmap.view.destination.state.LoadingState
import com.mdelbel.android.coolmap.view.destination.state.PickCityState
import com.mdelbel.android.coolmap.view.destination.state.ViewState
import com.mdelbel.android.domain.permissions.PermissionsDenied
import com.mdelbel.android.domain.permissions.PermissionsGranted
import com.mdelbel.android.domain.place.CityDetail
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.NullCity
import com.mdelbel.android.usecases.location.ObtainLocation
import com.mdelbel.android.usecases.permissions.AskLocationPermissions
import com.mdelbel.android.usecases.place.FilterCitiesByCountry
import com.mdelbel.android.usecases.place.FilterCitiesByLocation
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SelectCityViewModel @Inject constructor(
    private val askPermissionUseCase: AskLocationPermissions,
    private val obtainLocationUseCase: ObtainLocation,
    private val filterCitiesByLocationUseCase: FilterCitiesByLocation,
    private val filterCitiesByCountryUseCase: FilterCitiesByCountry
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    internal val screenState = MutableLiveData<ViewState>().apply { setValue(LoadingState()) }

    fun askPermissions() = compositeDisposable.add(askPermissionAndListenerResponse())

    private fun askPermissionAndListenerResponse() = askPermissionUseCase().subscribe { it ->
        when (it) {
            is PermissionsGranted -> selectGeolocationCityIfCan()
            is PermissionsDenied -> requestAvailableCountries()
        }
    }

    fun countrySelected(country: Country) {
        screenState.postValue(LoadingState())
        val cities = filterCitiesByCountryUseCase(country)
        screenState.postValue(PickCityState(cities))
    }

    fun citySeleted(city: CityDetail) = notifyCitySelection(city)

    private fun selectGeolocationCityIfCan() = compositeDisposable.add(requestLocationAndListenerResponse())

    private fun requestLocationAndListenerResponse() = obtainLocationUseCase().subscribe { it ->
        compositeDisposable.add(
            filterCitiesByLocationUseCase(it).subscribe { matchingCity ->
                when (matchingCity) {
                    NullCity -> requestAvailableCountries()
                    else -> notifyCitySelection(matchingCity)
                }
            })
    }

    private fun requestAvailableCountries() {
        screenState.postValue(LoadingState())
        /* TODO obtainCountriesUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { screenState.postValue(PickCountryState(it)) }
            .dispose()
        */
    }

    private fun notifyCitySelection(city: CityDetail) = screenState.postValue(CitySelectedState(city))

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
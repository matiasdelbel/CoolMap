package com.mdelbel.android.coolmap.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mdelbel.android.coolmap.view.state.*
import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.permissions.PermissionsDenied
import com.mdelbel.android.domain.permissions.PermissionsGranted
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.City
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.NullCity
import com.mdelbel.android.usecases.location.ObtainLocation
import com.mdelbel.android.usecases.permissions.AskLocationPermissions
import com.mdelbel.android.usecases.place.FilterCitiesByCountry
import com.mdelbel.android.usecases.place.ObtainCities
import com.mdelbel.android.usecases.place.ObtainCountries
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class SelectCityViewModel(
    private val askPermissionUseCase: AskLocationPermissions,
    private val obtainLocationUseCase: ObtainLocation,
    private val obtainCitiesUseCase: ObtainCities,
    private val obtainCountriesUseCase: ObtainCountries,
    private val filterCitiesByCountryUseCase: FilterCitiesByCountry
) : ViewModel() {

    private val citiesStream = obtainCitiesUseCase()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    internal val screenState = MutableLiveData<ViewState>().apply { setValue(LoadingState()) }

    init {
        askPermissionUseCase()
            .subscribe {
                when (it) {
                    is PermissionsGranted -> selectGeolocationCityIfCan()
                    is PermissionsDenied -> requestAvailableCountries()
                }
            }
            .dispose()
    }

    fun countrySelected(country: Country) {
        screenState.postValue(LoadingState())
        filterCitiesByCountryUseCase(country)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { screenState.postValue(PickCityState(it)) }
            .dispose()
    }

    fun citySeleted(city: City) = notifyCitySelection(city)

    private fun selectGeolocationCityIfCan() {
        Observable.zip(obtainLocationUseCase(), citiesStream,
            BiFunction<Location, Cities, () -> Any> { location, cities ->
                val matchingCity = cities.pickMatchingCity(location)
                when (matchingCity) {
                    NullCity -> return@BiFunction { requestAvailableCountries() }
                    else -> return@BiFunction { notifyCitySelection(matchingCity) }
                }
            })
            .subscribe { functionToExecute -> functionToExecute() }
            .dispose()
    }

    private fun requestAvailableCountries() {
        screenState.postValue(LoadingState())
        obtainCountriesUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { screenState.postValue(PickCountryState(it)) }
            .dispose()
    }

    private fun notifyCitySelection(city: City) = screenState.postValue(CitySelectedState(city))
}
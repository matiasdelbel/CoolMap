package com.mdelbel.android.coolmap.view.destination

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mdelbel.android.coolmap.view.destination.state.*
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.permissions.PermissionsDenied
import com.mdelbel.android.domain.permissions.PermissionsGranted
import com.mdelbel.android.domain.place.city.City
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.city.NonExistentCity
import com.mdelbel.android.usecases.location.ObtainLocation
import com.mdelbel.android.usecases.permissions.AskLocationPermissions
import com.mdelbel.android.usecases.place.FilterCitiesByCountry
import com.mdelbel.android.usecases.place.FilterCitiesByLocation
import com.mdelbel.android.usecases.place.ObtainCountries
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SelectDestinationViewModel @Inject constructor(
    private val askPermissionUseCase: AskLocationPermissions,
    private val obtainLocationUseCase: ObtainLocation,
    private val filterCitiesByLocationUseCase: FilterCitiesByLocation,
    private val obtainCountriesUseCase: ObtainCountries,
    private val filterCitiesByCountryUseCase: FilterCitiesByCountry
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val stackMemento = StateStack()

    internal val screenState = MutableLiveData<DestinationViewState>().apply { setValue(LoadingState()) }

    fun askPermissions() = compositeDisposable.add(askPermissionAndListenerResponse())

    private fun askPermissionAndListenerResponse() = askPermissionUseCase().subscribe { it ->
        when (it) {
            is PermissionsGranted -> selectGeolocationCityIfCan()
            is PermissionsDenied -> requestAvailableCountries()
        }
    }

    fun countrySelected(country: Country) {
        screenState.postValue(LoadingState())
        compositeDisposable.add(
            filterCitiesByCountryUseCase(country)
                .subscribe({
                    publishStateQueueing(PickCityState(it))
                }, { publishError(NoCitiesFoundedErrorState) })
        )
    }

    fun citySelected(city: City) = notifyCitySelection(city)

    private fun selectGeolocationCityIfCan() = compositeDisposable.add(requestLocationAndListenerResponse())

    fun returnsStateBefore() = screenState.postValue(stackMemento.dequeue())

    private fun requestLocationAndListenerResponse() = obtainLocationUseCase().subscribe(
        { compositeDisposable.add(requestCitiesForLocationAndListenerResponse(it)) },
        { requestAvailableCountries() })

    private fun requestCitiesForLocationAndListenerResponse(location: LocationOnCountry) =
        filterCitiesByLocationUseCase(location).subscribe({ matchingCity ->
            when (matchingCity) {
                NonExistentCity -> requestAvailableCountries()
                else -> notifyCitySelection(matchingCity)
            }
        }, {
            screenState.postValue(NoCitiesFoundedErrorState)
        })

    private fun requestAvailableCountries() {
        screenState.postValue(LoadingState())
        compositeDisposable.add(
            obtainCountriesUseCase()
                .subscribe(
                    { publishStateQueueing(PickCountryState(it)) },
                    { publishError(NoCountriesFoundedErrorState) })
        )
    }

    private fun notifyCitySelection(city: City) = screenState.postValue(CitySelectedState(city))

    private fun publishStateQueueing(viewState: DestinationViewState) {
        stackMemento.queue(viewState)
        screenState.postValue(viewState)
    }

    private fun publishError(viewState: ErrorState) = screenState.postValue(viewState)

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
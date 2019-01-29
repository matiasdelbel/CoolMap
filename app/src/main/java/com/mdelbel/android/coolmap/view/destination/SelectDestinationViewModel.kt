package com.mdelbel.android.coolmap.view.destination

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mdelbel.android.coolmap.view.destination.state.*
import com.mdelbel.android.domain.location.UserLocation
import com.mdelbel.android.domain.permissions.PermissionsDenied
import com.mdelbel.android.domain.permissions.PermissionsGranted
import com.mdelbel.android.domain.place.CityDetail
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.NullCity
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
        compositeDisposable.add(
            filterCitiesByCountryUseCase(country)
                .subscribe({
                    publishStateQueueing(PickCityState(it))
                }, { publishError(NoCitiesFoundedErrorState) })
        )
    }

    fun citySelected(city: CityDetail) = notifyCitySelection(city)

    private fun selectGeolocationCityIfCan() = compositeDisposable.add(requestLocationAndListenerResponse())

    fun returnsStateBefore() = screenState.postValue(stackMemento.dequeue())

    private fun requestLocationAndListenerResponse() = obtainLocationUseCase().subscribe { it ->
        compositeDisposable.add(requestCitiesForLocationAndListenerResponse(it))
    }

    private fun requestCitiesForLocationAndListenerResponse(location: UserLocation) =
        filterCitiesByLocationUseCase(location).subscribe({ matchingCity ->
            when (matchingCity) {
                NullCity -> requestAvailableCountries()
                else -> notifyCitySelection(matchingCity)
            }
        }, {
            screenState.postValue(NoCitiesFoundedErrorState)
        })

    private fun requestAvailableCountries() {
        screenState.postValue(LoadingState())
        compositeDisposable.add(
            obtainCountriesUseCase()
                .subscribe({
                    publishStateQueueing(PickCountryState(it))
                }, { publishError(NoCountriesFoundedErrorState) })
        )
    }

    private fun notifyCitySelection(city: CityDetail) = screenState.postValue(CitySelectedState(city))

    private fun publishStateQueueing(viewState: ViewState) {
        stackMemento.queue(viewState)
        screenState.postValue(viewState)
    }

    private fun publishError(viewState: ErrorState) = screenState.postValue(viewState)

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
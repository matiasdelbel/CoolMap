package com.mdelbel.android.coolmap.di

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.mdelbel.android.coolmap.data.location.GoogleLocationRequester
import com.mdelbel.android.coolmap.data.permissions.MapPermissionsRequester
import com.mdelbel.android.coolmap.data.place.ApiCityDataSource
import com.mdelbel.android.coolmap.data.place.ApiCountryDataSource
import com.mdelbel.android.data.repository.CityRepository
import com.mdelbel.android.data.repository.CountryRepository
import com.mdelbel.android.data.requester.LocationRequester
import com.mdelbel.android.data.requester.PermissionsRequester
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class RequesterProvider {

    private val permissionsRequester = MapPermissionsRequester()

    @Provides
    fun providesRequester(): PermissionsRequester = permissionsRequester

    @Provides
    fun providesMapRequester(): MapPermissionsRequester = permissionsRequester
}

@Module
class LocationProvider @Inject constructor(context: Context) {

    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private val geoCoder = Geocoder(context)

    @Provides
    fun providesRequester(): LocationRequester = GoogleLocationRequester(fusedLocationProviderClient, geoCoder)
}

@Module
class CityProvider {

    @Provides
    fun providesRepository(): CityRepository = CityRepository(origin = ApiCityDataSource())
}

@Module
class CountryProvider {

    @Provides
    fun providesRepository(): CountryRepository = CountryRepository(origin = ApiCountryDataSource())
}
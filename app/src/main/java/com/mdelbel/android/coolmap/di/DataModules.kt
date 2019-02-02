package com.mdelbel.android.coolmap.di

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.mdelbel.android.coolmap.data.location.GoogleLocationRequester
import com.mdelbel.android.coolmap.data.permissions.MapPermissionsRequester
import com.mdelbel.android.coolmap.data.place.ApiCityDataSource
import com.mdelbel.android.coolmap.data.place.ApiCityDetailDataSource
import com.mdelbel.android.coolmap.data.place.ApiCountryDataSource
import com.mdelbel.android.data.repository.CityInfoRepository
import com.mdelbel.android.data.repository.CitiesRepository
import com.mdelbel.android.data.repository.CountriesRepository
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
    fun providesRepository(): CitiesRepository = CitiesRepository(origin = ApiCityDetailDataSource())
}

@Module
class CountryProvider {

    @Provides
    fun providesRepository(): CountriesRepository = CountriesRepository(origin = ApiCountryDataSource())
}

@Module
class CityInformationProvider {

    @Provides
    fun providesRepository(): CityInfoRepository = CityInfoRepository(origin = ApiCityDataSource())
}
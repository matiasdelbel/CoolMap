package com.mdelbel.android.coolmap.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mdelbel.android.coolmap.view.destination.SelectDestinationViewModel
import com.mdelbel.android.coolmap.view.map.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@Module(includes = [RequesterProvider::class, LocationProvider::class, CityProvider::class, CountryProvider::class])
abstract class SelectCityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SelectDestinationViewModel::class)
    abstract fun bindViewModel(viewModel: SelectDestinationViewModel): ViewModel
}

@Module
abstract class MapViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindViewModel(viewModel: MapViewModel): ViewModel
}
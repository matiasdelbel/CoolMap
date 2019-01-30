package com.mdelbel.android.coolmap.di

import com.mdelbel.android.coolmap.view.destination.SelectDestinationScreen
import com.mdelbel.android.coolmap.view.map.MapScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeSelectCityScreen(): SelectDestinationScreen

    @ContributesAndroidInjector
    abstract fun contributeMapScreen(): MapScreen

}
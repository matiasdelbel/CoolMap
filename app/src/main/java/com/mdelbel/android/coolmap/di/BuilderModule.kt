package com.mdelbel.android.coolmap.di

import com.mdelbel.android.coolmap.view.destination.SelectCityScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeSelectCityScreen(): SelectCityScreen

}
package com.mdelbel.android.coolmap.di

import com.mdelbel.android.coolmap.MapApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        BuilderModule::class,
        ViewModelModule::class,
        SelectCityViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(app: MapApplication)
}

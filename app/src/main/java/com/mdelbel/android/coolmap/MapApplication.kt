package com.mdelbel.android.coolmap

import android.app.Activity
import android.app.Application
import com.mdelbel.android.coolmap.di.*
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MapApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .locationProvider(LocationProvider(applicationContext))
            .cityProvider(CityProvider())
            .countryProvider(CountryProvider())
            .requesterProvider(RequesterProvider())
            .build()
            .inject(this)

    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? =  activityDispatchingAndroidInjector
}
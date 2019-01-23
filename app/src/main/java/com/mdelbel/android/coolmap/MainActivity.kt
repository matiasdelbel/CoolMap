package com.mdelbel.android.coolmap

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.mdelbel.android.coolmap.data.permissions.MapPermissionsRequester
import com.mdelbel.android.coolmap.data.permissions.RequesterActivityBinder
import com.mdelbel.android.coolmap.data.place.ApiCityDataSource
import com.mdelbel.android.usecases.place.ObtainCities
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private val permissionRequester = MapPermissionsRequester()
    private lateinit var requesterActivityBinder: RequesterActivityBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requesterActivityBinder = RequesterActivityBinder(permissionRequester, this)
        //TODO lifecycle.addObserver(requesterActivityBinder)

        ObtainCities(ApiCityDataSource())()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                Toast.makeText(this, "cities", Toast.LENGTH_LONG).show()
            }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionRequester.onRequestPermissionsResult(requestCode, grantResults)
    }
}
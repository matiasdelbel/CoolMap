package com.mdelbel.android.coolmap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.mdelbel.android.coolmap.data.permissions.MapPermissionsRequester
import com.mdelbel.android.coolmap.data.permissions.RequesterActivityBinder

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private val permissionRequester = MapPermissionsRequester()
    private lateinit var requesterActivityBinder: RequesterActivityBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requesterActivityBinder = RequesterActivityBinder(permissionRequester, this)
        lifecycle.addObserver(requesterActivityBinder)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionRequester.onRequestPermissionsResult(requestCode, grantResults)
    }
}
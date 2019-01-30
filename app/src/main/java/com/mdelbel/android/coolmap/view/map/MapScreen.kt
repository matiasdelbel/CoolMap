package com.mdelbel.android.coolmap.view.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.mdelbel.android.coolmap.R
import com.mdelbel.android.domain.place.CityDetail
import javax.inject.Inject

class MapScreen : AppCompatActivity(), OnMapReadyCallback {

    companion object {

        fun start(context: Context, cityDetail: CityDetail) {
            val intent = Intent(context, MapScreen::class.java)
            intent.putExtra(EXTRA_CITY_DETAIL, cityDetail.code())
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MapViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MapViewModel::class.java)
    }

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.screen_map_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }
}

private const val EXTRA_CITY_DETAIL = "EXTRA_CITY_DETAIL"
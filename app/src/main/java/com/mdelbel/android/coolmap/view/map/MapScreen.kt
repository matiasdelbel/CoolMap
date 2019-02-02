package com.mdelbel.android.coolmap.view.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.mdelbel.android.coolmap.R
import com.mdelbel.android.coolmap.view.map.state.MapViewState
import com.mdelbel.android.coolmap.view.map.state.MessageError
import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.city.City
import com.mdelbel.android.domain.place.city.CityInfo
import dagger.android.AndroidInjection
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

class MapScreen : AppCompatActivity(), OnMapReadyCallback, MapView {

    companion object {

        fun start(context: Context, cityDetail: City) {
            val intent = Intent(context, MapScreen::class.java)
            intent.putExtra(EXTRA_MAP_INPUT, MapScreenInput(cityDetail.code(), cityDetail.countryCode()))
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MapViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MapViewModel::class.java)
    }

    private lateinit var map: GoogleMap
    private lateinit var nameView: TextView
    private lateinit var countryView: TextView
    private lateinit var currencyView: TextView
    private lateinit var languageView: TextView
    private lateinit var errorView: TextView
    private lateinit var loadingView: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.screen_map)
        initViews()
    }

    private fun initViews() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.screen_map_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        nameView = findViewById(R.id.panel_city_information_city_name)
        countryView = findViewById(R.id.panel_city_information_country_code)
        currencyView = findViewById(R.id.panel_city_information_currency)
        languageView = findViewById(R.id.panel_city_information_language)
        errorView = findViewById(R.id.panel_city_information_error)
        loadingView = findViewById(R.id.panel_city_information_progress_bar)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        observeViewModelState()
        observeCameraChanges()

        viewModel.obtainGeolocationCityInformation(getExtraMapScreenInput().selectedCityCode)
        viewModel.obtainCitiesFor(Country(code = getExtraMapScreenInput().countryCode))
    }

    private fun observeViewModelState() =
        viewModel.screenState.observe(this, Observer<MapViewState> { it.render(this@MapScreen) })

    private fun observeCameraChanges() {
        map.setOnCameraIdleListener {
            val cameraPosition = map.cameraPosition

            viewModel.onNewCenter(Location(cameraPosition.target.latitude, cameraPosition.target.longitude))
            viewModel.onNewZoomLevel(ZoomLevel(cameraPosition.zoom))
        }
    }

    private fun getExtraMapScreenInput() = intent.getParcelableExtra<MapScreenInput>(EXTRA_MAP_INPUT)

    override fun showCityInformation(city: CityInfo) {
        nameView.text = city.name()
        countryView.text = city.countryCode()
        currencyView.text = city.currency()
        languageView.text = city.language()

        changeVisibility(View.VISIBLE, nameView, countryView, currencyView, languageView)
        changeVisibility(View.GONE, errorView, loadingView)
    }

    override fun showWorkingAreas(areas: List<List<LatLng>>) {
        map.clear()
        for (area in areas) {
            map.addPolygon(
                PolygonOptions()
                    .addAll(area).strokeWidth(0.0f)
                    .fillColor(ContextCompat.getColor(this, R.color.colorPrimaryLight))
            )
        }
    }

    override fun showMarkers(points: List<LatLng>) {
        map.clear()
        points.forEach { point -> map.addMarker(MarkerOptions().position(point)) }

        map.setOnMarkerClickListener {
            viewModel.obtainCitiesFor(
                LocationOnCountry(
                    Location(it.position.latitude, it.position.longitude),
                    Country(code = getExtraMapScreenInput().countryCode)
                )
            )
            true
        }
    }

    override fun moveTo(locations: List<LatLng>) {
        var latLngBoundsBuilder = LatLngBounds.Builder()
        for (location in locations) {
            latLngBoundsBuilder = latLngBoundsBuilder.include(location)
        }

        map.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBoundsBuilder.build(), 10))
    }

    override fun loading() {
        changeVisibility(View.GONE, nameView, countryView, currencyView, languageView, errorView)
        changeVisibility(View.VISIBLE, loadingView)
    }

    override fun showError(error: MessageError) {
        errorView.text = error.show(this)

        changeVisibility(View.GONE, nameView, countryView, currencyView, languageView, loadingView)
        changeVisibility(View.VISIBLE, errorView)
    }

    private fun changeVisibility(visibility: Int, vararg views: View) {
        views.forEach { view -> view.visibility = visibility }
    }
}

@Parcelize
data class MapScreenInput(val selectedCityCode: String, val countryCode: String) : Parcelable

private const val EXTRA_MAP_INPUT = "EXTRA_MAP_INPUT"
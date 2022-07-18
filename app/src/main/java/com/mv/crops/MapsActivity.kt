package com.mv.crops


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.diego.test.openWeatherAPI.WeatherAPIConfig

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.mv.crops.databinding.ActivityMapsBinding
import java.net.MalformedURLException
import java.net.URL

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var tileOverlay: TileOverlay
    private lateinit var tileType: String
    private lateinit var cropLocation: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cropLocation = LatLng(getIntent().getExtras()!!.getDouble("latitude"),
                                getIntent().getExtras()!!.getDouble("longitude"))


        var spinner = findViewById<View>(R.id.tileType) as Spinner

        val tileName = arrayOf<String?>(
            "Nubes",
            "Temperatura",
            "Precipitaciones",
            "Lluvia",
            "Viento",
            "Presion atmosferica"
        )

        val adpt: ArrayAdapter<*> = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, tileName)

        spinner.setAdapter(adpt)
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Check click
                when (position) {
                    0 -> tileType = "clouds"
                    1 -> tileType = "temp"
                    2 -> tileType = "precipitation"
                    3 -> tileType = "rain"
                    4 -> tileType = "wind"
                    5 -> tileType = "pressure"
                }

                if (mMap != null) {
                    tileOverlay.remove()
                    tileOverlay = mMap.addTileOverlay(
                        TileOverlayOptions()
                            .tileProvider(tileProvider)
                    )!!
                }
            }
        })

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.addMarker(MarkerOptions().position(cropLocation).title("Ubicacion del cultivo"))
        //Move the camera to the crop's location and zoom in
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cropLocation, 12.0f));

        tileOverlay = mMap.addTileOverlay(
            TileOverlayOptions()
                .tileProvider(tileProvider)
        )!!

    }

    var tileProvider: TileProvider = object : UrlTileProvider(256, 256) {

        override fun getTileUrl(x: Int, y: Int, zoom: Int): URL? {

            /* Define the URL pattern for the tile images */
            val url =
                "https://tile.openweathermap.org/map/${tileType}_new/${zoom}/${x}/${y}.png?appid=${WeatherAPIConfig.appID}"

            if (!checkTileExists(x, y, zoom)) {
                return null
            }

            try {
                return URL(url)
            }
            catch (e: MalformedURLException) {
                throw AssertionError(e)
            }

        }

        /*
         * Check that the tile server supports the requested x, y and zoom.
         * Complete this stub according to the tile range you support.
         * If you support a limited range of tiles at different zoom levels, then you
         * need to define the supported x, y range at each zoom level.
         */
        private fun checkTileExists(x: Int, y: Int, zoom: Int): Boolean {
            val minZoom = 1
            val maxZoom = 25
            return zoom in minZoom..maxZoom
        }
    }

}
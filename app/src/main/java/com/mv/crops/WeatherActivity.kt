package com.mv.crops

import Models.CurrentWeatherResponse
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.diego.test.openWeatherAPI.WeatherAPIConfig
import com.diego.test.openWeatherAPI.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_weather)

        var boton_mapa = findViewById<Button>(R.id.weather_btn_mapa)
        var temperatura = findViewById<TextView>(R.id.weather_txt_temp)
        var viento = findViewById<TextView>(R.id.weather_txt_viento)
        var lluvia = findViewById<TextView>(R.id.weather_txt_lluvia)
        var humedad = findViewById<TextView>(R.id.weather_txt_humedad)
        var amanecer = findViewById<TextView>(R.id.weather_txt_amanecer)
        var ocaso = findViewById<TextView>(R.id.weather_txt_ocaso)
        var latitude = 0.0
        var longitude = 0.0

        try {

            val city = "Bogota"

            val retrofit = Retrofit.Builder()
                .baseUrl(WeatherAPIConfig.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(WeatherService::class.java)
            val call = service.getCurrentWeatherByCity(city,
                WeatherAPIConfig.units,
                WeatherAPIConfig.language,
                WeatherAPIConfig.appID)

            call.enqueue(object: Callback<CurrentWeatherResponse> {

                override fun onResponse(call: Call<CurrentWeatherResponse>, response: Response<CurrentWeatherResponse>) {
                    if (response.code() == 200) {

                        val weatherResponse = response.body()!!

                        temperatura.text = "Temp: " + weatherResponse.main!!.temp.toString() + " C"
                        viento.text =  "Viento: " + weatherResponse.wind!!.speed.toString() + " m/s"

                        if (weatherResponse.rain?.h1 != null) {
                            lluvia.text = "Lluvia 1h: " + weatherResponse.rain?.h1.toString() + " mm"
                        }
                        else {
                            lluvia.text = "Lluvia 1h: 0 mm"
                        }

                        humedad.text = "Humedad: " + weatherResponse.main!!.humidity.toString() + " %"

                        var pattern = "HH:mm";
                        var timeFormat = SimpleDateFormat(pattern);

                        amanecer.text = "Amanecer: " + timeFormat.format(Date(1000 * weatherResponse.sys?.sunrise!!.toLong()))
                        ocaso.text = "Ocaso: " + timeFormat.format(Date(1000 * weatherResponse.sys?.sunset!!.toLong()))

                        latitude = weatherResponse.coord!!.lat.toDouble()
                        longitude = weatherResponse.coord!!.lon.toDouble()

                    }

                }

                override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                    throw Exception(t.message)
                }

            })

        }
        catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

        boton_mapa.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("latitude", latitude)
            intent.putExtra("longitude", longitude)
            startActivity(intent)
        }

    }
}
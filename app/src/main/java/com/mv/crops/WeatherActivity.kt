package com.mv.crops

import Models.Crop
import Models.CurrentWeatherResponse
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.diego.test.openWeatherAPI.WeatherAPIConfig
import com.diego.test.openWeatherAPI.WeatherService
import com.github.mikephil.charting.data.PieEntry
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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

    private lateinit var auth: FirebaseAuth;
    private val db = Firebase.firestore

    private lateinit var temperatura: TextView
    private lateinit var lluvia: TextView
    private lateinit var viento: TextView
    private lateinit var humedad: TextView
    private lateinit var amanecer: TextView
    private lateinit var ocaso: TextView

    var latitude = 0.0
    var longitude = 0.0
    var city = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        auth = Firebase.auth

        var cultivo = getIntent().getExtras()!!.getString("cultivo")
        println("cultivo de $cultivo")
        setContentView(R.layout.activity_weather)

        var boton_mapa = findViewById<Button>(R.id.weather_btn_mapa)
        temperatura = findViewById<TextView>(R.id.weather_txt_temp)
        viento = findViewById<TextView>(R.id.weather_txt_viento)
        lluvia = findViewById<TextView>(R.id.weather_txt_lluvia)
        humedad = findViewById<TextView>(R.id.weather_txt_humedad)
        amanecer = findViewById<TextView>(R.id.weather_txt_amanecer)
        ocaso = findViewById<TextView>(R.id.weather_txt_ocaso)

        val boton_regresar = findViewById<ImageView>(R.id.weather_btn_regresar)

        boton_regresar.setOnClickListener {
            val intent = Intent(this, CropActivity::class.java)
            intent.putExtra("cultivo",cultivo)
            startActivity(intent)
        }

        val docRef = db.collection("crops/${auth.currentUser!!.email}/cultivos").document(cultivo!!)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    city = document.data!!["ubicacion"].toString()
                    getWeatherData()
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

        boton_mapa.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("latitude", latitude)
            intent.putExtra("longitude", longitude)
            startActivity(intent)
        }

    }

    private fun getWeatherData() {
        try {

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
    }

}
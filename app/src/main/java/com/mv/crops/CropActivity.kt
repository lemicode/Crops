package com.mv.crops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class CropActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_crop)

        var cultivo = getIntent().getExtras()!!.getString("cultivo")

        var texto_cultivo = findViewById<TextView>(R.id.crop_txt_cultivo)
        var boton_horas_trabajadas = findViewById<Button>(R.id.crop_btn_horas)
        var boton_agua_regada = findViewById<Button>(R.id.crop_btn_agua)
        var boton_hectareas_trabajadas = findViewById<Button>(R.id.crop_btn_hectareas)
        val boton_regresar = findViewById<ImageView>(R.id.crop_btn_regresar)
        var boton_balance = findViewById<Button>(R.id.crop_btn_balance)
        var boton_clima = findViewById<Button>(R.id.crop_btn_clima)

        boton_balance.setOnClickListener {
            val intent = Intent(this, BalanceActivity::class.java)
            intent.putExtra("cultivo",cultivo)
            startActivity(intent)
        }

        boton_clima.setOnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            intent.putExtra("cultivo",cultivo)
            startActivity(intent)
        }

        boton_regresar.setOnClickListener {
            val intent = Intent(this, CropsViewActivity::class.java)
            startActivity(intent)
        }

        texto_cultivo.text = "Cultivo de $cultivo"

        boton_horas_trabajadas.setOnClickListener {
            val intent = Intent(this, HoursWorkedActivity::class.java)
            intent.putExtra("cultivo",cultivo)
            startActivity(intent)
        }

        boton_agua_regada.setOnClickListener {
            val intent = Intent(this, IrrigatedWaterActivity::class.java)
            intent.putExtra("cultivo",cultivo)
            startActivity(intent)
        }

        boton_hectareas_trabajadas.setOnClickListener {
            val intent = Intent(this, HectaresWorkedActivity::class.java)
            intent.putExtra("cultivo",cultivo)
            startActivity(intent)
        }

    }
}
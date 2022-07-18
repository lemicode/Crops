package com.mv.crops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView

class CropActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_crop)

        var cultivo = getIntent().getExtras()!!.getString("cultivo")

        var boton_informe = findViewById<Button>(R.id.crop_btn_reporte)
        var texto_cultivo = findViewById<TextView>(R.id.crop_txt_cultivo)
        var boton_horas_trabajadas = findViewById<Button>(R.id.crop_btn_horas)
        var boton_agua_regada = findViewById<Button>(R.id.crop_btn_agua)
        var boton_hectareas_trabajadas = findViewById<Button>(R.id.crop_btn_hectareas)

        texto_cultivo.text = "Cultivo de $cultivo"

        boton_informe.setOnClickListener {
            val intent = Intent(this, ReportActivity::class.java)
            intent.putExtra("cultivo",cultivo)
            startActivity(intent)
        }

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
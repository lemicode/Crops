package com.mv.crops

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class HoursWorkedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hours_worked)

        var cultivo = getIntent().getExtras()!!.getString("cultivo")

        val txt_tiempo_trabajado = findViewById<EditText>(R.id.hours_worked_txt_time_worked)
        val txt_fecha = findViewById<EditText>(R.id.hours_worked_txt_fecha)
        val boton_anadir = findViewById<Button>(R.id.hours_worked_btn_anadir)

        boton_anadir.setOnClickListener {
            try {
                val tiempo_trabajado = txt_tiempo_trabajado.text.toString()
                val fecha = txt_fecha.text.toString()
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }

    }
}
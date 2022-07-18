package com.mv.crops

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class IrrigatedWaterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_irrigated_water)

        var cultivo = getIntent().getExtras()!!.getString("cultivo")

        val txt_cantidad_agua = findViewById<EditText>(R.id.irrigated_water_txt_amount)
        val boton_anadir = findViewById<Button>(R.id.irrigated_water_btn_anadir)

        boton_anadir.setOnClickListener {
            try {
                val cantidad_agua = txt_cantidad_agua.text.toString()
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }

    }
}